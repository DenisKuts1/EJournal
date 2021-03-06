package com.chnu.ejournal.fragments.subjects

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chnu.ejournal.App
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.LabCreator
import com.chnu.ejournal.entities.Subject
import com.chnu.ejournal.fragments.AppFragmentManager
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleSubject
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "email"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SubjectsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SubjectsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SubjectsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var email: String? = null
    lateinit var appFragmentManager: AppFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_PARAM1)
        }
    }

    lateinit var subjectList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_subjects, container, false)

        subjectList = view.findViewById<RecyclerView>(R.id.subjects_recycler_view)

        return view
    }

    fun updateList() {
        val adapter = SubjectsAdapter()

        adapter.setListener { position ->
            appFragmentManager.openSubjectFragment((adapter.getItem(position) as SubjectsItemBody).subject)

        }
        val items = LabCreator.subjects

        adapter.setItems(items)
        val manager = LinearLayoutManager(context)
        subjectList.layoutManager = manager
        subjectList.adapter = adapter
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SubjectsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email: String) =
                SubjectsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, email)
                    }
                }
    }
}
