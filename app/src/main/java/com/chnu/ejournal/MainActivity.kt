package com.chnu.ejournal

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.View
import com.chnu.ejournal.entities.Group
import com.chnu.ejournal.entities.LabCreator
import com.chnu.ejournal.entities.Student
import com.chnu.ejournal.fragments.AppFragmentManager
import com.chnu.ejournal.fragments.schedule.ScheduleFragment
import com.chnu.ejournal.fragments.settings.SettingsFragment
import com.chnu.ejournal.fragments.subjects.SubjectsFragment
import com.chnu.ejournal.retrofit.MyRetrofitApi
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    lateinit var appFragmentManager: AppFragmentManager
    lateinit var navigation: BottomNavigationView

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
        navigation = findViewById(R.id.navigation)

        Observable.create<String> { subscriber ->
            try {
                MyRetrofitApi.init(this)

                val lessons = MyRetrofitApi.getLessons(intent.extras["id"] as Long).body()
                LabCreator.lessons.clear()
                LabCreator.lessons.addAll(lessons!!)

                LabCreator.week = MyRetrofitApi.getWeekNumber().body()!!
                val groupIds = ArrayList<Long>()
                LabCreator.lessons.forEach { lesson ->

                    if (!groupIds.contains(lesson.groupId)) {
                        groupIds += lesson.groupId
                    }
                }
                LabCreator.groups.clear()
                groupIds.forEach { id ->
                    val groupDto = MyRetrofitApi.getGroup(id).body()!!
                    val students = MyRetrofitApi.getStudentsOfGroup(id).body()!!
                    val group = Group(groupDto.id, groupDto.name)
                    students.forEach { student ->
                        group.students += Student(student.id, "${student.name} ${student.surname}", student.subGroup)
                    }
                    LabCreator.groups += group
                }

                LabCreator.subjects.clear()
                LabCreator.lessons.forEach { lesson ->
                    val labs = MyRetrofitApi.getLabsOfSubject(lesson.lessonId).body()
                    val subject = LabCreator.subjectFromLesson(lesson)
                    subject.labs += labs!!

                    subject.labs.forEach { lab ->
                        lab.points = HashMap()
                        subject.group.students.forEach { student ->
                            val point = MyRetrofitApi.getPointOfStudent(student.id, lab.id).body()!!

                            lab.points[student] = point.roundToInt()
                        }
                    }

                    LabCreator.subjects += subject
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
            subscriber.onNext("")
            subscriber.onCompleted()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            appFragmentManager = AppFragmentManager(supportFragmentManager, baseContext, navigation, window)
                            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
                            navigation.selectedItemId = R.id.navigation_schedule
                        },
                        { e ->

                        }
                )
    }

    override fun onBackPressed() {
        if (appFragmentManager.onBackPressed()) {
            super.onBackPressed()
        }
    }
}
