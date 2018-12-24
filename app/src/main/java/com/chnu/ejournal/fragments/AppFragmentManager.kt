package com.chnu.ejournal.fragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject
import com.chnu.ejournal.fragments.schedule.ScheduleFragment
import com.chnu.ejournal.fragments.settings.SettingsFragment
import com.chnu.ejournal.fragments.subject.SubjectFragment
import com.chnu.ejournal.fragments.subjects.SubjectsFragment

class AppFragmentManager {
    lateinit var manager: FragmentManager

    private var current: Fragment? = null
    private val settingsFragment = SettingsFragment()
    private val subjectsFragment = SubjectsFragment()
    private val scheduleFragment = ScheduleFragment()
    private val subjectFragment = SubjectFragment()

    init {
        scheduleFragment.appFragmentManager = this
    }

    fun openSubjectFragment(subject: Subject){
        val transaction = manager.beginTransaction()
        if (current !is SubjectFragment) {
            subjectFragment.subject = subject
            if (current != null) {
                transaction.hide(current!!)
            }
            if (subjectFragment.isAdded) {
                transaction.show(subjectFragment)
            } else {
                transaction.add(R.id.main_layout_place_holder, subjectFragment)
            }
        }
        current = subjectFragment
        transaction.commitAllowingStateLoss()
    }

    fun openScheduleFragment(){
        val transaction = manager.beginTransaction()
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
        transaction.commitAllowingStateLoss()
    }

    fun openSubjectListFragment(){
        val transaction = manager.beginTransaction()
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
        transaction.commitAllowingStateLoss()
    }

    fun openSettingsFragment(){
        val transaction = manager.beginTransaction()
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
        transaction.commitAllowingStateLoss()
    }
}