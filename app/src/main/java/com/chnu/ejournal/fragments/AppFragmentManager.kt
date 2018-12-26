package com.chnu.ejournal.fragments

import android.content.Context
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.LabCreator
import com.chnu.ejournal.entities.Student
import com.chnu.ejournal.entities.Subject
import com.chnu.ejournal.fragments.labs.LabsFragment
import com.chnu.ejournal.fragments.schedule.ScheduleFragment
import com.chnu.ejournal.fragments.settings.SettingsFragment
import com.chnu.ejournal.fragments.subject.SubjectFragment
import com.chnu.ejournal.fragments.subjects.SubjectsFragment
import java.util.*

class AppFragmentManager(val manager: FragmentManager, val context: Context, val navigation: BottomNavigationView) {


    private var current: Fragment
    private var previous: Fragment
    private val settingsFragment = SettingsFragment()
    private val subjectsFragment = SubjectsFragment()
    private val scheduleFragment = ScheduleFragment()
    private val subjectFragment = SubjectFragment()
    private val labsFragment = LabsFragment()
    init {
        subjectFragment.subject = LabCreator.subjects[0]
        scheduleFragment.appFragmentManager = this
        subjectFragment.appFragmentManager = this
        labsFragment.appFragmentManager = this
        manager.beginTransaction().add(R.id.main_layout_place_holder, subjectFragment).hide(subjectFragment).commit()
        manager.beginTransaction().add(R.id.main_layout_place_holder, labsFragment).hide(labsFragment).commit()
        manager.beginTransaction().add(R.id.main_layout_place_holder, settingsFragment).hide(settingsFragment).commit()
        manager.beginTransaction().add(R.id.main_layout_place_holder, subjectsFragment).hide(subjectsFragment).commit()
        manager.beginTransaction().add(R.id.main_layout_place_holder, scheduleFragment).commit()
        current = scheduleFragment
        previous = current
    }

    fun openSubjectFragment(subject: Subject){
        navigation.visibility = View.GONE
        navigation.refreshDrawableState()
        subjectFragment.updateSubject(subject)
        manager.beginTransaction().hide(current).show(subjectFragment).commit()
        previous = current
        current = subjectFragment
    }

    fun openLabFragment(student: Student,subject: Subject){
        navigation.visibility = View.GONE
        navigation.refreshDrawableState()
        labsFragment.setUp(student, subject)
        manager.beginTransaction().hide(current).show(labsFragment).commit()
        current = labsFragment
    }

    fun openScheduleFragment(){
        navigation.visibility = View.VISIBLE
        manager.beginTransaction().hide(current).show(scheduleFragment).commit()
        previous = current
        current = scheduleFragment
    }

    fun openSubjectListFragment(){
        navigation.visibility = View.VISIBLE
        manager.beginTransaction().hide(current).show(subjectsFragment).commit()
        previous = current
        current = subjectsFragment
    }

    fun openSettingsFragment(){
        navigation.visibility = View.VISIBLE
        manager.beginTransaction().hide(current).show(settingsFragment).commit()
        previous = current
        current = settingsFragment
    }

    fun onBackPressed(): Boolean{
        return when(current){
            is SubjectFragment -> {
                navigation.visibility = View.VISIBLE
                manager.beginTransaction().hide(current).show(previous).commitAllowingStateLoss()
                current = previous
                false
            }
            is LabsFragment -> {
                manager.beginTransaction().hide(current).show(subjectFragment).commitAllowingStateLoss()
                current = subjectFragment
                false
            }
            else -> true
        }

    }
}