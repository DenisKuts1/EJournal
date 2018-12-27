package com.chnu.ejournal.fragments.subject

import android.content.res.ColorStateList
import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.chnu.ejournal.*
import com.chnu.ejournal.entities.Group
import com.chnu.ejournal.entities.Student
import com.chnu.ejournal.entities.Subject
import com.chnu.ejournal.fragments.AppFragmentManager
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleSubject

class SubjectFragment : Fragment() {

    private lateinit var group: Group
    lateinit var subject: Subject
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var toolbar: Toolbar
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    lateinit var studentRecyclerView: RecyclerView
    lateinit var image: ImageView
    lateinit var groupText: TextView
    lateinit var fab: FloatingActionButton
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var appFragmentManager: AppFragmentManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_subject, container, false)

        appBarLayout = view.findViewById(R.id.subject_appbar_layout)
        toolbar = view.findViewById(R.id.subject_toolbar)
        (activity as MainActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as MainActivity).supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        collapsingToolbarLayout = view.findViewById(R.id.subject_collapsing_toolbar_layout)

        groupText = view.findViewById(R.id.subject_appbar_group_text)

        image = view.findViewById(R.id.subject_appbar_image)

        studentRecyclerView = view.findViewById(R.id.subject_students_list)
        studentRecyclerView.layoutManager = LinearLayoutManager(context)


        fab = view.findViewById(R.id.subject_report_fab)
        coordinatorLayout = view.findViewById(R.id.subject_root_layout)
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(p0: AppBarLayout?, verticalOffset: Int) {
                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    coordinatorLayout.setBackgroundColor(resources.getColor(R.color.colorBackground))

                } else {
                    if (::subject.isInitialized) {
                        coordinatorLayout.setBackgroundColor(subject.getPrimaryImageColor(context!!))
                    }
                }
            }
        })
        return view
    }

    fun updateSubject(newSubject: Subject) {
        group = Group("543м/1")
        toolbar.title = subject.name
        subject = newSubject
        image.setImageDrawable(subject.getImage(context!!))
        groupText.text = "${group.number} гр 45%"
        toolbar.title = subject.name
        fab.backgroundTintList = ColorStateList.valueOf(subject.getPrimaryImageColor(context!!))
        coordinatorLayout.setBackgroundColor(subject.getPrimaryImageColor(context!!))
        collapsingToolbarLayout.setContentScrimColor(subject.getPrimaryImageColor(context!!))


        val students = subject.group.students
        studentRecyclerView.layoutManager = LinearLayoutManager(context)

        val size = Point()
        activity!!.windowManager.defaultDisplay.getSize(size)
        val adapter = StudentsAdapter(subject.getPrimaryImageColor(context!!))
        adapter.setUpItems(students, subject)

        adapter.setListener {position ->
            println(adapter.items[position].student.name)
            appFragmentManager.openLabFragment(adapter.items[position].student, subject)

        }

        studentRecyclerView.adapter = adapter


    }


}