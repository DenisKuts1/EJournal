package com.chnu.ejournal.fragments.labs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chnu.ejournal.R

class LabsFragment: Fragment(){
    lateinit var viewMain: View
    val toolbar: Toolbar by lazy { viewMain.findViewById<Toolbar>(R.id.labs_toolbar) }
    val recycledView: RecyclerView by lazy { viewMain.findViewById<RecyclerView>(R.id.labs_recycled_view) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewMain = inflater.inflate(R.layout.fragment_subject, container, false)

        return viewMain
    }
}