package cz.muni.log4ts.webservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// TODO 3. RetrofitUtil -> Vytvoření instance Api
object RetrofitUtil {

    // TODO 3. RetrofitUtil -> URL
    private const val BASE_URL = "https://api.waqi.info/"

    // TODO 3. RetrofitUtil -> Vyvoření instnace AqiAPI
    fun createAqiWebService(): AqiApi =
        create(BASE_URL, createOkHttpClient())

    // TODO 3. RetrofitUtil -> Generická metoda, jež vytvoří jakýkoliv interface
    private inline fun <reified T> create(baseUrl: String, okHttpClient: OkHttpClient): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(T::class.java)

    // TODO 3. RetrofitUtil -> Vytvoření OkHttpClinta
    // TODO 3. RetrofitUtil -> lze zde definovat interceptory, token autentifikatory atd...
    private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            addInterceptor(logging)
        }.build()
}