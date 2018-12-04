package com.chnu.ejournal

import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.View
import com.chnu.ejournal.fragments.schedule.ScheduleFragment
import com.chnu.ejournal.fragments.settings.SettingsFragment
import com.chnu.ejournal.fragments.subject.SubjectsFragment
import com.chnu.ejournal.retrofit.MyRetrofitApi
import com.chnu.ejournal.retrofit.TestResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var current: Fragment? = null
    private val settingsFragment = SettingsFragment()
    private val subjectsFragment = SubjectsFragment()
    private val scheduleFragment = ScheduleFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_schedule -> {
                if (current !is ScheduleFragment) {
                    if (current != null) {
                        transaction.hide(current!!)
                    }
                    if (scheduleFragment.isAdded) {
                        transaction.show(scheduleFragment)
                    } else {
                        transaction.add(R.id.main_layout_place_holder, scheduleFragment)
                    }
                }
                current = scheduleFragment
            }
            R.id.navigation_subject -> {
                if (current !is SubjectsFragment) {
                    if (current != null) {
                        transaction.hide(current!!)
                    }
                    if (subjectsFragment.isAdded) {
                        transaction.show(subjectsFragment)
                    } else {
                        transaction.add(R.id.main_layout_place_holder, subjectsFragment)
                    }
                }
                current = subjectsFragment
            }
            R.id.navigation_settings -> {
                if (current !is SettingsFragment) {
                    if (current != null) {
                        transaction.hide(current!!)
                    }
                    if (settingsFragment.isAdded) {
                        transaction.show(settingsFragment)
                    } else {
                        transaction.add(R.id.main_layout_place_holder, settingsFragment)
                    }
                }
                current = settingsFragment
            }
        }
        transaction.commit()
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
}
