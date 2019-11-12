package ericdiaz.program.newsapp.network.test

import ericdiaz.program.newsapp.model.NewsResponse
import ericdiaz.program.newsapp.network.BaseNetwork
import ericdiaz.program.newsapp.network.NewsService
import io.reactivex.Observable

object FakeNetworkModule : BaseNetwork {

    override fun getNewsService(): NewsService {
        return FakeNewsService
    }
}


object FakeNewsService : NewsService {
    override fun getNews(country: String, apiKey: String): Observable<NewsResponse> {
        return Observable.just(NewsResponse.EMPTY)
    }

}