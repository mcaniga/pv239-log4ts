package cz.muni.log4ts.ui.list

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.R
import cz.muni.log4ts.data.AqiPresentableListItem
import cz.muni.log4ts.databinding.ItemAiqListBinding
import cz.muni.log4ts.util.AqiScale

class AqiViewHolder(private val binding: ItemAiqListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        listItem: AqiPresentableListItem,
        onItemClick: (AqiPresentableListItem) -> Unit,
        onFavoriteClick: (AqiPresentableListItem, Int) -> Unit
    ) {
        val aqiColor = AqiScale.getColor(listItem.aqi)
        binding.aqiColorIndicator.backgroundTintList = ContextCompat.getColorStateList(itemView.context, aqiColor)

        binding.aqiValueTextView.text = listItem.aqi
        binding.cityNameTextView.text = listItem.station
        binding.timeTextView.text = listItem.time

        binding.cardContainer.setOnClickListener {
            onItemClick(listItem)
        }

        val imageRes = if (listItem.isFavorite) R.drawable.ic_heart else R.drawable.ic_heart_outline
        binding.favoriteImageView.setImageResource(imageRes)

        binding.favoriteImageView.setOnClickListener {
            onFavoriteClick(listItem, adapterPosition)
        }
    }
}