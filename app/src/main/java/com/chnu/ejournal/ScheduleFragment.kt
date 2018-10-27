package com.chnu.ejournal

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "email"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ScheduleFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ScheduleFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScheduleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email: String) =
                ScheduleFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, email)

                    }
                }
    }
}
