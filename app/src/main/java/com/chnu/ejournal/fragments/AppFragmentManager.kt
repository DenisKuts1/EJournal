package com.chnu.ejournal.fragments

import android.content.Context
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.RelativeLayout
import com.chnu.ejournal.MainActivity
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject
import com.chnu.ejournal.fragments.schedule.ScheduleFragment
import com.chnu.ejournal.fragments.settings.SettingsFragment
import com.chnu.ejournal.fragments.subject.SubjectFragment
import com.chnu.ejournal.fragments.subjects.SubjectsFragment
import java.util.*

class AppFragmentManager(val manager: FragmentManager, val context: Context) {

    lateinit var navigation: BottomNavigationView

    private var current: Fragment
    private val settingsFragment = SettingsFragment()
    private val subjectsFragment = SubjectsFragment()
    private val scheduleFragment = ScheduleFragment()
    private val subjectFragment = SubjectFragment()

    init {
        subjectFragment.subject = Subject(context,"123", "321", Date(), 1)
        scheduleFragment.appFragmentManager = this
        manager.beginTransaction().add(R.id.main_layout_place_holder, subjectFragment).hide(subjectFragment).commit()
        manager.beginTransaction().add(R.id.main_layout_place_holder, settingsFragment).hide(settingsFragment).commit()
        manager.beginTransaction().add(R.id.main_layout_place_holder, subjectsFragment).hide(subjectsFragment).commit()
        manager.beginTransaction().add(R.id.main_layout_place_holder, scheduleFragment).commit()
        current = scheduleFragment
    }

    fun openSubjectFragment(subject: Subject){
        navigation.visibility = View.GONE
        navigation.refreshDrawableState()
        subjectFragment.updateSubject(subject)
        manager.beginTransaction().hide(current).show(subjectFragment).commit()
        current = subjectFragment
    }

    fun openScheduleFragment(){
        manager.beginTransaction().hide(current).show(scheduleFragment).commit()
        current = scheduleFragment
    }

    fun openSubjectListFragment(){
        manager.beginTransaction().hide(current).show(subjectsFragment).commit()
        current = subjectsFragment
    }

    fun openSettingsFragment(){
        manager.beginTransaction().hide(current).show(settingsFragment).commit()
        current = settingsFragment
    }
}