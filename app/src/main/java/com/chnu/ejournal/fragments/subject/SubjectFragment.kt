package com.chnu.ejournal.fragments.subject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chnu.ejournal.Group
import com.chnu.ejournal.MainActivity
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject

class SubjectFragment: Fragment() {

    private lateinit var group: Group
    lateinit var subject: Subject


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_subject, container, false)
        println(123123123)
        val toolbar = view.findViewById<Toolbar>(R.id.subject_toolbar)
        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.title = subject.name
        val image = view.findViewById<ImageView>(R.id.subject_appbar_image)
        image.setImageDrawable(subject.getImage())
        //val title = view.findViewById<TextView>(R.id.subject_toolbar_title)
        //title.text = subject.name
        return view
    }
}