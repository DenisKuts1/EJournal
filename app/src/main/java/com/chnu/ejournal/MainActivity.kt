package com.chnu.ejournal

import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.View
import com.chnu.ejournal.fragments.AppFragmentManager
import com.chnu.ejournal.fragments.schedule.ScheduleFragment
import com.chnu.ejournal.fragments.settings.SettingsFragment
import com.chnu.ejournal.fragments.subjects.SubjectsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var appFragmentManager: AppFragmentManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_schedule -> {
                appFragmentManager.openScheduleFragment()
            }
            R.id.navigation_subject -> {
                appFragmentManager.openSubjectListFragment()
            }
            R.id.navigation_settings -> {
                appFragmentManager.openSettingsFragment()
            }
        }

        navigation.refreshDrawableState()
        true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting up night mode
        if (App.instance.isNightModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

        setContentView(R.layout.activity_main)
        appFragmentManager = AppFragmentManager(supportFragmentManager, baseContext, navigation,window)
        /*first_button.setOnClickListener {
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
        }*/

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_schedule

    }

    override fun onBackPressed() {
        if(appFragmentManager.onBackPressed()){
            super.onBackPressed()
        }
    }
}
