package cz.muni.aqicheck.ui.list

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import cz.muni.aqicheck.R
import cz.muni.aqicheck.`data`.AqiPresentableListItem
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class ListFragmentDirections private constructor() {
  private data class ActionListFragmentToDetailFragment(
    public val item: AqiPresentableListItem
  ) : NavDirections {
    public override val actionId: Int = R.id.action_listFragment_to_detailFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(AqiPresentableListItem::class.java)) {
          result.putParcelable("item", this.item as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(AqiPresentableListItem::class.java)) {
          result.putSerializable("item", this.item as Serializable)
        } else {
          throw UnsupportedOperationException(AqiPresentableListItem::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionListFragmentToDetailFragment(item: AqiPresentableListItem): NavDirections =
        ActionListFragmentToDetailFragment(item)
  }
}
