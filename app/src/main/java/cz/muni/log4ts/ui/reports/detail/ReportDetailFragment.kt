package cz.muni.log4ts.ui.reports.detail

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import cz.muni.log4ts.Log4TSApplication
import cz.muni.log4ts.R
import cz.muni.log4ts.data.dto.ReportDetailDto
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.databinding.FragmentReportDetailBinding
import cz.muni.log4ts.mapper.LogEntryMapper
import cz.muni.log4ts.util.ErrorHandler
import kotlinx.coroutines.launch
import java.io.FileWriter
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class ReportDetailFragment : Fragment() {

    @Inject
    lateinit var logEntriesDetailAction: ReportDetailFragmentAction

    @Inject
    lateinit var mapper: LogEntryMapper

    private val TAG = ReportDetailFragment::class.simpleName

    private lateinit var binding: FragmentReportDetailBinding


    private lateinit var projectName: String

    private lateinit var userId: String

    private lateinit var username: String

    private lateinit var lowerBound: Date

    private lateinit var upperBound: Date

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportDetailBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        val reportDetailDto: ReportDetailDto = extractReportDetailDtoFromArgs()
        setBackButton(view)
        inicializeDtoViews(reportDetailDto)

        val recyclerViewAdapter = ReportDetailRecyclerViewAdapter(viewLifecycleOwner, view, findNavController())
        setRecyclerView(recyclerViewAdapter)
        getReportDetails(recyclerViewAdapter, view)
        exportCsvOnButtonClick(view, recyclerViewAdapter)
    }

    private fun setRecyclerView(recyclerViewAdapter: ReportDetailRecyclerViewAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    private fun getReportDetails(
        recyclerViewAdapter: ReportDetailRecyclerViewAdapter,
        view: View
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            logEntriesDetailAction.getReportDetailsEntriesOrShowError(
                userId,
                projectName,
                lowerBound,
                upperBound,
                recyclerViewAdapter,
                view
            )
        }
    }

    private fun exportCsvOnButtonClick(view: View, recyclerViewAdapter: ReportDetailRecyclerViewAdapter) {
        val currTime = DateTimeFormatter.ISO_INSTANT.format(Instant.now()).replace(":", ".")
        binding.save.setOnClickListener {
            val downloadsDirectoryPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
            val reportFilename = "/${username}_${projectName}_${currTime}.csv"
            val csvPath = downloadsDirectoryPath + reportFilename;
            writeCsvFile(
     downloadsDirectoryPath + reportFilename,
                recyclerViewAdapter.listItems
            )
            ErrorHandler.showActionWasSucessfullSnackbar(view, format("CSV saved to: %s", csvPath))
        }
    }

    private fun writeCsvFile(fileName: String, data: List<LogEntry>) {
        val csvMapper = CsvMapper().apply {
            registerModule(KotlinModule())
        }
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        FileWriter(fileName).use { writer ->
            csvMapper.writer(csvMapper.schemaFor(LogEntry::class.java).withHeader())
                .writeValues(writer)
                .writeAll(data.map { entry -> mapper.makeFirebaseStringDataMapFromLogEntry(entry, formatter) })
                .close()
        }
    }

    private fun inicializeDtoViews(reportDetailDto: ReportDetailDto) {
        binding.totalTimeVal.text = reportDetailDto.minutes.toString()
        binding.usernameTextview.text = reportDetailDto.username
        binding.totaltimeTextView.text = "Total time in sec."
        projectName = reportDetailDto.project
        userId = reportDetailDto.userId
        username = reportDetailDto.username
        lowerBound = reportDetailDto.lowerBound
        upperBound = reportDetailDto.upperBound
    }

    private fun injectDependencies() {
        Log4TSApplication.appComponent.injectReportDetailFragmentDeps(this)
    }

    private fun extractReportDetailDtoFromArgs() =
        ReportDetailFragmentArgs.fromBundle(requireArguments()).item

    private fun setBackButton(view: View) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            ErrorHandler.safelyNavigateUp(findNavController(), TAG, view)
        }
    }

}