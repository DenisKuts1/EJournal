package com.chnu.ejournal.fragments.subjects

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_subjects, container, false)

        val subjectList = view.findViewById<RecyclerView>(R.id.subjects_recycler_view)
        val adapter = SubjectsAdapter()
        val items = arrayListOf(
                Subject(context!!, "Networking", "242/1 group", Date(118, 11, 1, 9, 40), 14),
                Subject(context!!, "Math analysis", "341/2 group", Date(118, 11, 1, 13, 0), 11),
                Subject(context!!, "Cryptography", "341/2 group", Date(118, 11, 1, 8, 20), 8),
                Subject(context!!, "Coding with Mironiv", "143/2 group", Date(118, 11, 1, 9, 50), 10),
                Subject(context!!, "Computer architecture", "143/2 group", Date(118, 11, 3, 9, 50), 9),
                Subject(context!!, "Quality assurance", "341/2 group", Date(118, 11, 2, 8, 20), 13),
                Subject(context!!, "Computer architecture", "143/2 group", Date(118, 11, 3, 8, 20), 8),
                Subject(context!!, "Computer architecture", "143/2 group", Date(118, 11, 4, 9, 50), 8),
                Subject(context!!, "Computer architecture", "143/2 group", Date(118, 11, 5, 8, 20), 8),
                Subject(context!!, "Computer architecture", "143/2 group", Date(118, 11, 5, 9, 50), 8))

        adapter.setItems(items)
        val manager = LinearLayoutManager(context)
        subjectList.layoutManager = manager
        subjectList.adapter = adapter

        return view
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
