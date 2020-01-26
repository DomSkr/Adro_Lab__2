package com.kubickiengineering.laboratorium__2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.kubickiengineering.laboratorium__2.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    val disposables = CompositeDisposable()

    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient())
            .build()

    val api: UserDataApi = retrofit.create(UserDataApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            it.lifecycleOwner = this
            it.viewModel = this.viewModel
        }

        przyciskLogowania?.setOnClickListener { _ ->
//            postUser(api)
//            doNetworkCall()
            viewModel.getMoreData()
        }

//        from.setText(viewModel.d.value)
    }

    private fun postUser(api: UserDataApi) {
        val fromTitle = from.text.toString()
        val sbjct = subject.text.toString()
        val txt = text.text.toString()

        val user = FormModel(
                "TRALALA",
                email = fromTitle,
                subject = sbjct,
                message = txt
        )

        api.postUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { model, error ->
                    if (error == null) {
                        Toast.makeText(this@MainActivity, model.toString(), Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity, error.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                .addTo(disposables)
    }

    private fun doNetworkCall() {
        api.getUserAsync().enqueue(object : Callback<PersonSW> {
            override fun onFailure(call: Call<PersonSW>, t: Throwable) {

                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<PersonSW>, response: Response<PersonSW>) {


                Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_LONG).show()

            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}


interface Boom {
    fun boom(): String
}

class View1() : Boom {

    fun createPresenter(): Presenter {
        return Presenter(this)
    }

    fun foo(): String {
        return "foo"
    }

    override fun boom(): String {
        return "boom"
    }

}


class Presenter(val boom: Boom) {

    internal val view: Boom? = null

    fun downloadData(): String {
        tralala()
        return boom.boom()
    }
}

fun tralala(): String {
    return "asdasdasd"
}