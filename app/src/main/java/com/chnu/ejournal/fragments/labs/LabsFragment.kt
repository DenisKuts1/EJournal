package com.chnu.ejournal.fragments.labs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chnu.ejournal.MainActivity
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.Student
import com.chnu.ejournal.entities.Subject
import com.chnu.ejournal.fragments.AppFragmentManager
import com.chnu.ejournal.fragments.dialogs.AddPointDialog
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleSubject

class LabsFragment: Fragment(){
    lateinit var viewMain: View
    lateinit var toolbar: Toolbar
    val recycledView: RecyclerView by lazy { viewMain.findViewById<RecyclerView>(R.id.labs_recycled_view) }
    lateinit var appFragmentManager: AppFragmentManager

    lateinit var student: Student
    lateinit var subject: Subject
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewMain = inflater.inflate(R.layout.labs_fragment, container, false)
        toolbar = viewMain.findViewById(R.id.labs_toolbar)
        (activity as MainActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as MainActivity).supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        return viewMain
    }

    fun setUp(newStudent: Student, newSubject: Subject){
        student = newStudent
        subject = newSubject
        toolbar.title = student.name
        val adapter = LabAdapter(subject.getPrimaryImageColor(context!!))
        adapter.setItems(student, subject)
        adapter.setListener {position ->
            val lab = adapter.getItems(position).lab
            val dialog = AddPointDialog()
            dialog.lab = lab
            dialog.student = student
            dialog.show(activity!!.supportFragmentManager, "tag")

        }
        recycledView.layoutManager = LinearLayoutManager(context)
        recycledView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun setUp(){
        val adapter = LabAdapter(subject.getPrimaryImageColor(context!!))
        adapter.setItems(student, subject)
        adapter.setListener {position ->
            val lab = adapter.getItems(position).lab
            val dialog = AddPointDialog()
            dialog.appFragmentManager = appFragmentManager
            dialog.lab = lab
            dialog.student = student
            dialog.labsFragment = this
            dialog.show(activity!!.supportFragmentManager, "tag")

        }
        recycledView.layoutManager = LinearLayoutManager(context)
        recycledView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}