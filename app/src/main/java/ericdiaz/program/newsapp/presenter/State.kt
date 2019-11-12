package ericdiaz.program.newsapp.presenter

import ericdiaz.program.newsapp.model.NewsResponse

sealed class State {

    data class Success(val newsResponse: NewsResponse) : State()

    data class Failure(val throwable: Throwable) : State()

    object Loading : State()

    object Complete : State()
}