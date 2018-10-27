package com.chnu.ejournal

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var current: Fragment? = null
    private val settingsFragment by lazy { SettingsFragment.newInstance(intent.getStringExtra("email")) }
    private val subjectsFragment by lazy { SubjectsFragment.newInstance(intent.getStringExtra("email")) }
    private val scheduleFragment by lazy { ScheduleFragment.newInstance(intent.getStringExtra("email")) }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_schedule -> {
                val transaction = supportFragmentManager.beginTransaction()
                if(current != null){
                    transaction.remove(current!!)
                }
                transaction.add(R.id.main_container, scheduleFragment).commit()
                current = scheduleFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_subject -> {
                val transaction = supportFragmentManager.beginTransaction()
                if(current != null){
                    transaction.remove(current!!)
                }
                transaction.add(R.id.main_container, subjectsFragment).commit()
                current = subjectsFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                val transaction = supportFragmentManager.beginTransaction()
                if(current != null){
                    transaction.remove(current!!)
                }
                transaction.add(R.id.main_container, settingsFragment).commit()
                current = settingsFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "${intent.getStringExtra("email")}: ${intent.getStringExtra("token")}", Toast.LENGTH_SHORT).show()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}
