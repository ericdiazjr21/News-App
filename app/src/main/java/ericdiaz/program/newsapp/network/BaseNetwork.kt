package ericdiaz.program.newsapp.network

interface BaseNetwork {

    fun getNewsService(): NewsService
}