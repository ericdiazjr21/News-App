package ericdiaz.program.newsapp.network

import ericdiaz.program.newsapp.model.NewsResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org"
private const val PATH = "v2/top-headlines"

class NetworkModule {

    companion object {

        private val loggingInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

        private val loggingOkHttpClient: OkHttpClient =
            OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(loggingOkHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        fun getNewsService(): NewsService = retrofit.create(NewsService::class.java)
    }
}

interface NewsService {
    @GET(PATH)
    fun getNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "bf55d1d5740747198c59af77466416d1"
    ): Observable<NewsResponse>
}