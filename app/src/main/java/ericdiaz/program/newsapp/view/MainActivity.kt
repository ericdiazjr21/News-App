package ericdiaz.program.newsapp.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView
import ericdiaz.program.newsapp.MainContract
import ericdiaz.program.newsapp.R
import ericdiaz.program.newsapp.presenter.NewsPresenter
import ericdiaz.program.newsapp.presenter.State
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter = NewsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter
            .intent(
                RxView.clicks(data_request_button)
                    .debounce(3000, TimeUnit.MILLISECONDS)
                    //mocking some input
                    .doOnNext { Log.d("Sasuke", it.toString()) }
                    .map { UiModel(Box("us")) }
            )

    }

    override fun render(state: State) {
        when (state) {
            is State.Success -> makeToast(state.newsResponse.toString())
            is State.Failure -> makeToast(state.throwable.toString())
            is State.Loading -> makeToast("Loading")
            is State.Complete -> makeToast("Complete")
        }
    }

    private fun makeToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}