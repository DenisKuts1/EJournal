package com.chnu.ejournal

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.view.View
import android.view.Window
import com.chnu.ejournal.retrofit.MyRetrofitApi
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_launch.*
import retrofit2.Retrofit
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception
import java.util.*

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        //Setting up language
        val locale = Locale(App.instance.language)
        val config = baseContext.resources.configuration
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        super.onCreate(savedInstanceState)
        window.statusBarColor = resources.getColor(R.color.launch_status_bar);
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // Setting up night mode
        if (App.instance.isNotificationsEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_launch)

        // Managing authentication into google account
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account != null) {
            beginWork(account.email!!, account.idToken!!)
        }
        google_sign_in_button.setOnClickListener {
            val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.server_id))
                    .requestEmail()
                    .build()
            val client = GoogleSignIn.getClient(this, options)
            val intent = client.signInIntent
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)

        val email = account!!.email
        beginWork(email!!, account.idToken!!)
    }

    fun beginWork(email: String, status: String){
        //Auth on server
        Observable.create<String> {
            subscriber ->
            try {
                MyRetrofitApi.init(this)
                println(status)
                val response = MyRetrofitApi.auth(status)
                if(response.isSuccessful){
                    response.headers().names().forEach {
                        println("$it: ${response.headers().get(it)}")

                    }
                    val user = response.body()
                    println(response.body()!!.id)
                    /*val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", "qwe")
                    intent.putExtra("token", status)
                    startActivity(intent)*/
                } else {

                }
            } catch (e: Exception){
                e.printStackTrace()
            }
            subscriber.onNext("")
            subscriber.onCompleted()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { retrievedNews ->

                },
                { e ->

                }
        )

        // For testing without server only
        /*val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("token", status)
        startActivity(intent)*/
    }
}
