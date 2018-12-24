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
import android.view.animation.Animation
import android.widget.LinearLayout
import android.widget.TextView
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleHeader
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleItemType


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
    private lateinit var dayText: TextView
    private lateinit var dateText: TextView

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

        dayText = mainView.findViewById(R.id.schedule_day_text)
        dateText = mainView.findViewById(R.id.schedule_date_text)

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        dayText.text = resources.getStringArray(R.array.days_of_week)[calendar.get(Calendar.DAY_OF_WEEK) - 1]
        dateText.text = resources.getStringArray(R.array.months)[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DAY_OF_MONTH)

        initSubjectsList()
        initDaysList()
        return mainView
    }

    lateinit var scheduleAdapter: ScheduleAdapter
    lateinit var scheduleList: RecyclerView

    fun initDaysList() {
        val daysList = mainView.findViewById<RecyclerView>(R.id.schedule_days_list_view)

        val items = arrayListOf(Date(2018, 11, 1),
                Date(118, 11, 2),
                Date(118, 11, 3),
                Date(118, 11, 4),
                Date(118, 11, 5))


        val adapter = ScheduleDaysAdapter { date ->
            val position = scheduleAdapter.getPosition(date)
            scrollToPosition(position)

        }
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.setItems(items)
        daysList.adapter = adapter
        daysList.layoutManager = manager
    }

    fun scrollToPosition(position: Int) {
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

        val items = arrayListOf(
                Subject("Networking", "242/1 group", Date(118, 11, 1, 9, 40), 14),
                Subject("Math analysis", "341/2 group", Date(118, 11, 1, 13, 0), 11),
                Subject("Cryptography", "341/2 group", Date(118, 11, 1, 8, 20), 8),
                Subject("Coding with Mironiv", "143/2 group", Date(118, 11, 1, 9, 50), 10),
                Subject("Computer architecture", "143/2 group", Date(118, 11, 3, 9, 50), 9),
                Subject("Quality assurance", "341/2 group", Date(118, 11, 2, 8, 20), 13),
                Subject("Computer architecture", "143/2 group", Date(118, 11, 3, 8, 20), 8),
                Subject("Computer architecture", "143/2 group", Date(118, 11, 4, 9, 50), 8),
                Subject("Computer architecture", "143/2 group", Date(118, 11, 5, 8, 20), 8),
                Subject("Computer architecture", "143/2 group", Date(118, 11, 5, 9, 50), 8))
        scheduleAdapter = ScheduleAdapter(context!!)
        val manager = LinearLayoutManager(context)
        scheduleAdapter.setItems(items)
        scheduleList.adapter = scheduleAdapter
        scheduleList.layoutManager = manager
        scheduleAdapter.setListener { position ->

        }

        scheduleList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentFirstVisible = (scheduleList.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                if (currentFirstVisible != lastFirstVisible) {
                    if (dy > 0) {
                        val lastVisibleItem = scheduleAdapter.getItem(lastFirstVisible)
                        if (lastVisibleItem is ScheduleHeader) {
                            val newTitleDay = resources.getStringArray(R.array.days_of_week)[lastVisibleItem.calendar.get(Calendar.DAY_OF_WEEK) - 1]
                            val newTitleDate = resources.getStringArray(R.array.months)[lastVisibleItem.calendar.get(Calendar.MONTH)] + " " + lastVisibleItem.calendar.get(Calendar.DAY_OF_MONTH)
                            textAnimation(newTitleDay, newTitleDate)
                        }
                    } else {
                        if (currentFirstVisible != 0) {
                            val currentVisibleItem = scheduleAdapter.getItem(currentFirstVisible - 1)
                            if (currentVisibleItem is ScheduleHeader) {
                                val calendar = Calendar.getInstance()
                                calendar.time = currentVisibleItem.calendar.time
                                calendar.add(Calendar.DAY_OF_MONTH, -1)
                                val newTitleDay = resources.getStringArray(R.array.days_of_week)[calendar.get(Calendar.DAY_OF_WEEK) - 1]
                                val newTitleDate = resources.getStringArray(R.array.months)[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DAY_OF_MONTH)
                                textAnimation(newTitleDay, newTitleDate)
                            }
                        }
                    }
                    lastFirstVisible = currentFirstVisible
                }
            }
        })
    }

    val title_container: LinearLayout by lazy { mainView.findViewById<LinearLayout>(R.id.schedule_title) }

    fun textAnimation(newTitleDay: String, newTitleDate: String) {
        val animation = AlphaAnimation(1f, 0f)
        animation.duration = 200
        animation.repeatCount = 1
        animation.repeatMode = Animation.REVERSE
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {
                dayText.text = newTitleDay
                dateText.text = newTitleDate
            }
        })
        title_container.findViewById<LinearLayout>(R.id.schedule_title).startAnimation(animation)
    }

    var lastFirstVisible = 0

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
        scheduleList.setPaddingRelative(0, (px * percentage).toInt(), 0, px.toInt())
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
