package ericdiaz.program.newsapp.presenter

import ericdiaz.program.newsapp.MainContract
import ericdiaz.program.newsapp.network.NetworkModule
import ericdiaz.program.newsapp.view.UiModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class NewsPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun intent(uiModel: Observable<UiModel>) {

        compositeDisposable.add(
            uiModel
                .flatMap {
                    NetworkModule
                        .getNewsService()
                        .getNews(it.box.value as String)
                        .flatMap(
                            { newsResponse ->
                                Observable.just(State.Success(newsResponse))
                            },
                            { throwable ->
                                Observable.just(State.Failure(throwable))
                            },
                            {
                                Observable.just(State.Complete)
                            }

                        ).startWith(State.Loading)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { view.render(state = it) }
        )

    }
}