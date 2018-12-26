package com.chnu.ejournal.fragments.subject

import android.content.res.ColorStateList
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.chnu.ejournal.*

class SubjectFragment: Fragment() {

    private lateinit var group: Group
    lateinit var subject: Subject
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var toolbar: Toolbar
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    lateinit var studentRecyclerView: RecyclerView
    lateinit var adapter: StudentsAdapter
    lateinit var image: ImageView
    lateinit var groupText: TextView
    lateinit var fab:FloatingActionButton
    lateinit var coordinatorLayout:CoordinatorLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_subject, container, false)

        appBarLayout = view.findViewById(R.id.subject_appbar_layout)
        toolbar = view.findViewById(R.id.subject_toolbar)
        (activity as MainActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as MainActivity).supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)



        collapsingToolbarLayout = view.findViewById(R.id.subject_collapsing_toolbar_layout)


        groupText = view.findViewById<TextView>(R.id.subject_appbar_group_text)

        image = view.findViewById<ImageView>(R.id.subject_appbar_image)
        //val title = view.findViewById<TextView>(R.id.subject_toolbar_title)
        //title.text = subject.name




        studentRecyclerView = view.findViewById(R.id.subject_students_list)
        studentRecyclerView.layoutManager = LinearLayoutManager(context)


        fab = view.findViewById<FloatingActionButton>(R.id.subject_report_fab)
        coordinatorLayout =  view.findViewById<CoordinatorLayout>(R.id.subject_root_layout)
        appBarLayout.addOnOffsetChangedListener(object :AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(p0: AppBarLayout?, verticalOffset: Int) {
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {

                    coordinatorLayout.setBackgroundColor(resources.getColor(R.color.colorBackground))

                }
                else
                {
                    if(subject!=null) {
                        coordinatorLayout.setBackgroundColor(subject.getPrimaryImageColor())
                    }
                }
            }
        })

        return view
    }

    fun updateSubject(newSubject: Subject){
        group = Group("543м/1")
        toolbar.title = subject.name
        subject = newSubject
        image.setImageDrawable(subject.getImage())
        groupText.text = "${group.number} group 45%"
        toolbar.title = subject.name
        fab.backgroundTintList = ColorStateList.valueOf(subject.getPrimaryImageColor())
        coordinatorLayout.setBackgroundColor(subject.getPrimaryImageColor())
        collapsingToolbarLayout.setContentScrimColor(subject.getPrimaryImageColor())


        val students = arrayListOf(Student("Куц Денис", group, 2, 100),
                Student("Ніколаєвич Владислав", group, 2, 95),
                Student("Чижевський Василь", group, 2, 90),
                Student("Великий Князь Архо Владислав", group, 2, 0),
                Student("Гаврилиця Федір", group, 2, 85),
                Student("Лабінськой Віктор", group, 2, 70),
                Student("Лисенко Юлія", group, 2, 70),
                Student("Лазоряк Олександр", group, 2, 90),
                Student("Твардовський Роман", group, 2, 80),
                Student("Слободяник Олексій", group, 2, 50),
                Student("Бахір Антоніна", group, 2, 50),

                Student("Куц Денис", group, 2, 100),
                Student("Ніколаєвич Владислав", group, 2, 95),
                Student("Чижевський Василь", group, 2, 90),
                Student("Великий Князь Архо Владислав", group, 2, 0),
                Student("Гаврилиця Федір", group, 2, 85),
                Student("Лабінськой Віктор", group, 2, 70),
                Student("Лисенко Юлія", group, 2, 70),
                Student("Лазоряк Олександр", group, 2, 90),
                Student("Твардовський Роман", group, 2, 80),
                Student("Слободяник Олексій", group, 2, 50),
                Student("Бахір Антоніна", group, 2, 50)
        )
        studentRecyclerView.layoutManager = LinearLayoutManager(context)

        val size = Point()
        activity!!.windowManager.defaultDisplay.getSize(size)
        val adapter = StudentsAdapter(subject.getPrimaryImageColor(), size.x)
        adapter.setItems(students)
        studentRecyclerView.adapter = adapter

    }


}