package com.chnu.ejournal

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var current: Fragment? = null
    private val settingsFragment by lazy { SettingsFragment.newInstance(intent.getStringExtra("email")) }
    private val subjectsFragment by lazy { SubjectsFragment.newInstance(intent.getStringExtra("email")) }
    private val scheduleFragment by lazy { ScheduleFragment.newInstance(intent.getStringExtra("email")) }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_schedule -> {
                val transaction = supportFragmentManager.beginTransaction()
                if(current != null){
                    transaction.remove(current!!)
                }
                transaction.add(R.id.main_container, scheduleFragment).commit()
                current = scheduleFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_subject -> {
                val transaction = supportFragmentManager.beginTransaction()
                if(current != null){
                    transaction.remove(current!!)
                }
                transaction.add(R.id.main_container, subjectsFragment).commit()
                current = subjectsFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                val transaction = supportFragmentManager.beginTransaction()
                if(current != null){
                    transaction.remove(current!!)
                }
                transaction.add(R.id.main_container, settingsFragment).commit()
                current = settingsFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (App.instance.isNightModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }


        setContentView(R.layout.activity_main)
        first_button.setOnClickListener {
            Observable.create<String> {
                subscriber ->
                var response: Response<TestResponse>?
                try {
                    response = MyRetrofitApi.justGet()
                } catch (e: Exception){
                    response = null
                    e.printStackTrace()
                }
                subscriber.onNext(response!!.body()!!.test)
                subscriber.onCompleted()
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { text ->
                                textView.text = text
                            },
                            { e ->

                            }
                    )
        }

        second_button.setOnClickListener {
           Observable.create<String> {
                subscriber ->
                var response: Response<TestResponse>?
                try {
                    response = MyRetrofitApi.secureGet()
                } catch (e: Exception){
                    response = null
                    e.printStackTrace()
                }
                subscriber.onNext(response!!.body()!!.test)
                subscriber.onCompleted()
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { text ->
                                textView.text = text
                            },
                            { e ->

                            }
                    )
        }
        //Toast.makeText(this, "${intent.getStringExtra("email")}: ${intent.getStringExtra("token")}", Toast.LENGTH_SHORT).show()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}
