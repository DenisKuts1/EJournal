package com.chnu.ejournal.fragments.schedule

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chnu.ejournal.MainActivity
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject
import java.util.*
import android.view.animation.AlphaAnimation
import android.widget.RelativeLayout
import android.widget.ToggleButton
import com.chnu.ejournal.fragments.schedule.days.ScheduleDaysAdapter
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleAdapter
import com.chnu.ejournal.fragments.schedule.subjects.ScrollLinearLayoutManager
import android.support.v7.widget.LinearSmoothScroller
import android.support.v4.os.HandlerCompat.postDelayed
import android.support.v4.widget.NestedScrollView
import android.util.TypedValue
import android.widget.TextView


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
class ScheduleFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private var email: String? = null
    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true
    private val mTitleContainer by lazy { mainView.findViewById<RelativeLayout>(R.id.schedule_title_container) }
    private val appBarLayout by lazy { mainView.findViewById<AppBarLayout>(R.id.schedule_app_bar_layout) }
    private lateinit var mainView: View
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_schedule, container, false)
        navigationView = activity!!.findViewById(R.id.navigation)
        //println(navigationView.y)

        appBarLayout.addOnOffsetChangedListener(this)
        appBarLayout.isClickable = true

        val dayText = mainView.findViewById<TextView>(R.id.schedule_day_text)
        val dateText = mainView.findViewById<TextView>(R.id.schedule_date_text)

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        dayText.text = resources.getStringArray(R.array.days_of_week)[calendar.get(Calendar.DAY_OF_WEEK) - 1]
        dateText.text = resources.getStringArray(R.array.months)[calendar.get(Calendar.MONTH) - 1] + " " + calendar.get(Calendar.DAY_OF_MONTH)

        initSubjectsList()
        initDaysList()

        scrollToPosition(1)


        return mainView
    }

    lateinit var scheduleAdapter: ScheduleAdapter
    lateinit var scheduleList: RecyclerView

    fun initDaysList() {
        val daysList = mainView.findViewById<RecyclerView>(R.id.schedule_days_list_view)

        val items = arrayListOf(Date(2018, 11, 1),
                Date(2018, 11, 2),
                Date(2018, 11, 3),
                Date(2018, 11, 4),
                Date(2018, 11, 5))


        val adapter = ScheduleDaysAdapter { date ->
            val position = scheduleAdapter.getPosition(date)
            scrollToPosition(position)

        }
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.setItems(items)
        daysList.adapter = adapter
        daysList.layoutManager = manager
    }

    fun scrollToPosition(position: Int){
        val smoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return LinearSmoothScroller.SNAP_TO_START
            }
        }
        smoothScroller.targetPosition = position
        (scheduleList.layoutManager as LinearLayoutManager).startSmoothScroll(smoothScroller)
    }

    fun initSubjectsList() {
        scheduleList = mainView.findViewById(R.id.schedule_view)

        val items = arrayListOf(Subject("Python programing", "242/1 group", Date(2018, 11, 1, 9, 40), 1),
                Subject("Cryptography", "341/2 group", Date(2018, 11, 1, 13, 0), 1),
                Subject("Cryptography", "341/2 group", Date(2018, 11, 2, 8, 20), 1),
                Subject("Computer architecture", "143/2 group", Date(2018, 11, 2, 9, 50), 1),
                Subject("Cryptography", "341/2 group", Date(2018, 11, 3, 8, 20), 1),
                Subject("Computer architecture", "143/2 group", Date(2018, 11, 3, 9, 50), 1),
                Subject("Computer architecture", "143/2 group", Date(2018, 11, 4, 8, 20), 1),
                Subject("Computer architecture", "143/2 group", Date(2018, 11, 4, 9, 50), 1),
                Subject("Computer architecture", "143/2 group", Date(2018, 11, 5, 8, 20), 1),
                Subject("Computer architecture", "143/2 group", Date(2018, 11, 5, 9, 50), 1))
        scheduleAdapter = ScheduleAdapter(context!!)
        val manager = LinearLayoutManager(context!!)
        scheduleAdapter.setItems(items)
        scheduleList.adapter = scheduleAdapter
        scheduleList.layoutManager = manager
        scheduleAdapter.setListener { position ->

        }
    }


    companion object {

        private const val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.99f
        private const val PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.01f
        private const val ALPHA_ANIMATIONS_DURATION = 200


        @JvmStatic
        fun newInstance(email: String) =
                ScheduleFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, email)
                    }
                }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, offset: Int) {
        //println(navigationView.y)
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = Math.abs(offset) / maxScroll.toFloat()
        handleAlphaOnTitle(percentage)
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        setAlpha(mTitleContainer, percentage)
        setAlpha(appBarLayout, 1 - percentage)
        val dip = 64f
        val r = resources
        val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.displayMetrics
        )
        scheduleList.setPaddingRelative(0, (px * percentage).toInt(), 0, 0)
    }

    fun setAlpha(view: View, percentage: Float) {
        view.alpha = percentage
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                setAlpha(view.getChildAt(i), percentage)
            }
        }
    }


    fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
        val alphaAnimation = if (visibility == View.VISIBLE)
            AlphaAnimation(1f, 0f)
        else
            AlphaAnimation(0f, 1f)

        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }
}
