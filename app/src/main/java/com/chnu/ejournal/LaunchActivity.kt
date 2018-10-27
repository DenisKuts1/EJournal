package com.chnu.ejournal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_launch.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception
import java.util.*

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_launch)
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



        /*val response = MyRetrofitApi.auth(status)
        if(response.isSuccessful){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", response.body().email)
            intent.putExtra("token", status)
            startActivity(intent)
        }*/
        Log.i("STATE", "12312312312313123123WOWOWOWOWOWOWOWOW123333333333331232313")
        Observable.create<String> {
            subscriber ->
            try {
                Log.i("STATE", "12312312312313123123WOWOWOWOWOWOWOWOW123333333333331232313")
                val response = MyRetrofitApi.auth(status)
                Log.i("STATE", "12312312312313123123WOWOWOWOWOWOWOWOW123333333333331232313")
                if(response.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", "qwe")
                    intent.putExtra("token", status)
                    startActivity(intent)
                    Log.i("STATE", "12312312312313123123WOWOWOWOWOWOWOWOW")
                } else {
                    Log.i("STATE", "WOWOWOWOWOWOWOWOW")
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


        /*val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("token", status)
        startActivity(intent)*/
    }
}
