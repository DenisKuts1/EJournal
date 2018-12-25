package com.chnu.ejournal.fragments.subject

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.chnu.ejournal.Group
import com.chnu.ejournal.MainActivity
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject

class SubjectFragment: Fragment() {

    private lateinit var group: Group
    lateinit var subject: Subject
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var toolbar: Toolbar
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_subject, container, false)

        appBarLayout = view.findViewById(R.id.subject_appbar_layout)
        toolbar = view.findViewById(R.id.subject_toolbar)
        (activity as MainActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as MainActivity).supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        group = Group("543м/1")
        toolbar.title = subject.name

        collapsingToolbarLayout = view.findViewById(R.id.subject_collapsing_toolbar_layout)
        collapsingToolbarLayout.setContentScrimColor(subject.getPrimaryImageColor())


        val groupText = view.findViewById<TextView>(R.id.subject_appbar_group_text)
        groupText.text = "${group.number} group 45%"

        val image = view.findViewById<ImageView>(R.id.subject_appbar_image)
        image.setImageDrawable(subject.getImage())
        //val title = view.findViewById<TextView>(R.id.subject_toolbar_title)
        //title.text = subject.name
        return view
    }


}