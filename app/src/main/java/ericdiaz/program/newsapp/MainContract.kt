package ericdiaz.program.newsapp

import ericdiaz.program.newsapp.presenter.State
import ericdiaz.program.newsapp.view.UiModel
import io.reactivex.Observable

interface MainContract {

    interface View {
        fun render(state: State)
    }

    interface Presenter {
        fun intent(uiModel: Observable<UiModel>)
    }
}