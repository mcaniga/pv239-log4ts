package cz.muni.log4ts.webservice

import cz.muni.log4ts.webservice.response.AqiDetailResponse
import cz.muni.log4ts.webservice.response.AqiListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// TODO 2.3 Definice AqiApi
interface AqiApi {

    @GET("search/")
    fun getSearchAqiByName(
        @Query("keyword") keyboard: String,
        @Query("token") token: String,
    ): Call<AqiListResponse>

    @GET("feed/@{id}/")
    fun getSearchAqiById(
        @Path("id") id: Long,
        @Query("token") token: String,
    ): Call<AqiDetailResponse>
}