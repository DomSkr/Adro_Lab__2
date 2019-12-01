package com.kubickiengineering.laboratorium__2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_main)
        przyciskLogowania?.setOnClickListener { _ ->
            //            val intent = Intent(this, AbsenceActivity::class.java)
//            startActivity(intent)
            postUser(api)
        }
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