package cz.muni.log4ts.repository

import android.content.Context
import cz.muni.log4ts.data.AqiPresentableListItem
import cz.muni.log4ts.database.AqiDatabase
import cz.muni.log4ts.database.FavoriteStationDao
import cz.muni.log4ts.extension.getNowFormattedDateString
import cz.muni.log4ts.webservice.AqiApi
import cz.muni.log4ts.webservice.RetrofitUtil
import cz.muni.log4ts.webservice.response.AqiDetailResponse
import cz.muni.log4ts.webservice.response.AqiListItem
import cz.muni.log4ts.webservice.response.AqiListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AqiRepository(
    context: Context,
    private val favoriteStationDao: FavoriteStationDao = AqiDatabase.create(context).favoriteStationDao(),
    // TODO 4. vytvoření instance
    private val aqiApi: AqiApi = RetrofitUtil.createAqiWebService()
) {

    private val token: String = "ebb5ee840b6a6590cfcc2e354b7c26b135a21cb0"

    // TODO 6.2 lze smazat
    fun getMockedData(count: Int = 10): List<AqiPresentableListItem> =
        mutableListOf<AqiPresentableListItem>().apply {
            repeat(count) {
                val item = AqiPresentableListItem(
                    id = it.toLong(),
                    aqi = "$it",
                    time = System.currentTimeMillis().getNowFormattedDateString(),
                    station = "station-$it",
                    isFavorite = it % 2 == 0,
                )
                add(item)
            }
        }

    // TODO 4. search metoda
    fun search(keyword: String, onSuccess: (List<AqiPresentableListItem>) -> Unit, onFailure: (Throwable) -> Unit) {
        aqiApi.getSearchAqiByName(keyword, token)
            .enqueue(object : Callback<AqiListResponse> {

                override fun onResponse(call: Call<AqiListResponse>, response: Response<AqiListResponse>) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        onSuccess(mapAqi(responseBody.data))
                    } else {
                        onFailure(IllegalStateException("Response was not successful"))
                    }
                }

                override fun onFailure(call: Call<AqiListResponse>, t: Throwable) {
                    onFailure(t)
                }
            })
    }

    // TODO 6.1 (S) napsání mapovací funkce, která
    // TODO 6.1 z response napapuje na AqiPresentableListItem
    // TODO 6.1 signatury Využití toAqiPresentableItem
    private fun mapAqi(items: List<AqiListItem>): List<AqiPresentableListItem> {
        val favouritesIds = favoriteStationDao.getAll().map { it.id }
        return items.map { aqiItem ->
            val isFavourite = favouritesIds.any{id -> id == aqiItem.uid}
            aqiItem.toAqiPresentableItem(isFavourite)
        }
    }

    fun getFavorites(): List<AqiPresentableListItem> =
        favoriteStationDao.getAll()
            .map { entity ->
                // TODO 5. použítí mapper
                entity.toAqiPresentableItem()
            }

    fun updateFavorite(item: AqiPresentableListItem) {
        val isFavorite = !item.isFavorite
        if (isFavorite) {
            // TODO 5. použítí mapper
            favoriteStationDao.saveEntity(item.toFavoriteStationEntity())
        } else {
            favoriteStationDao.deleteById(item.id)
        }
    }

    // TODO 8. (S) -> napsání getStationById
    // TODO 8. (S) -> getSearchAqiById
    // TODO 8. (S) -> do DetailFragment se nyní nebude předávat item, ale pouze id
    // TODO 8. (S) -> a zobrazí se AqiDetailResponse
    fun getStationById(id: Long, onSuccess: (AqiDetailResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        aqiApi.getSearchAqiById(id = id, token = token).enqueue(object: Callback<AqiDetailResponse> {
            override fun onResponse(
                call: Call<AqiDetailResponse>,
                response: Response<AqiDetailResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    onSuccess(responseBody);
                }
            }

            override fun onFailure(call: Call<AqiDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}