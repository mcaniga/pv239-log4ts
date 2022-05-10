package cz.muni.log4ts.ui.reports

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.log4ts.Log4TSApplication
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.databinding.FragmentReportBinding
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.ui.projects.ProjectsFragmentExtractor
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class ReportFragment : Fragment() {

    @Inject
    lateinit var reportAction: ReportFragmentAction

    @Inject
    lateinit var projectsFragmentExtractor: ProjectsFragmentExtractor

    @Inject
    lateinit var firebaseAuthDao: FirebaseAuthDao

    @Inject
    lateinit var projectSpinnerAdapterFactory: ProjectSpinnerAdapterFactory

    @Inject
    lateinit var firebaseProjectRepository: FirebaseProjectRepository

    private lateinit var binding: FragmentReportBinding

    private var lowerCalendar: Calendar = Calendar.getInstance()

    private var upperCalendar: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        val recyclerViewAdapter =
            ReportRecyclerViewAdapter(viewLifecycleOwner, view, findNavController())
        setRecyclerView(recyclerViewAdapter)
        populateProjectSpinner()
        refreshRecyclerViewOnButtonClick(view, recyclerViewAdapter)
        setDefaultCalendars()
        getReport(recyclerViewAdapter, view)
        setBothCalendarPopups()
    }

    private fun getReport(
        recyclerViewAdapter: ReportRecyclerViewAdapter,
        view: View
    ) {
        var project: String? = binding.projectsSpinner.selectedItem?.toString()
        if (project == null) {
            project = "x"
        }
        viewLifecycleOwner.lifecycleScope.launch {
            reportAction.getReportEntriesOrShowError(
                project,
                lowerCalendar.time,
                upperCalendar.time,
                recyclerViewAdapter,
                view
            )
        }
    }

    private fun injectDependencies() {
        Log4TSApplication.appComponent.injectReportFragmentDeps(this)
    }

    private fun setRecyclerView(recyclerViewAdapter: ReportRecyclerViewAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    private fun setDefaultCalendars() {
        setMinCalendar(lowerCalendar)
        setMaxCalendar(upperCalendar)
    }

    private fun setMinCalendar(calendar: Calendar): Calendar {
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar
    }

    private fun setMaxCalendar(calendar: Calendar): Calendar {
        calendar.set(Calendar.YEAR, 2030)
        calendar.set(Calendar.MONTH, 12)
        calendar.set(Calendar.DAY_OF_MONTH, 28)
        return calendar
    }

    private fun setBothCalendarPopups() {
        setCalendarPopup(lowerCalendar, binding.lowerCalendar)
        setCalendarPopup(upperCalendar, binding.upperCalendar)

    }
    private fun setCalendarPopup(calendar: Calendar, editText: EditText) {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(calendar, editText)
            }
        editText.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

    private fun updateDateInView(calendar: Calendar, editText: EditText) {
        val myFormat = "yyyy/MM/dd/"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        editText.setText( sdf.format(calendar.time))
    }

    private fun populateProjectSpinner() {
        viewLifecycleOwner.lifecycleScope.launch {
            val projectSpinnerAdapter = projectSpinnerAdapterFactory.makeProjectSpinnerAdapter(
                firebaseAuthDao.getCurrentUserId().toString(),
                requireContext(),
                firebaseProjectRepository
            )
            binding.projectsSpinner.adapter = projectSpinnerAdapter
        }
    }

    private fun refreshRecyclerViewOnButtonClick(
        view: View,
        recyclerViewAdapter: ReportRecyclerViewAdapter
    ) {
        binding.applyFilters.setOnClickListener {
            val project = binding.projectsSpinner.selectedItem.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                reportAction.getReportEntriesOrShowError(
                    project,
                    lowerCalendar.time,
                    upperCalendar.time,
                    recyclerViewAdapter,
                    view
                )
            }
        }
    }

}