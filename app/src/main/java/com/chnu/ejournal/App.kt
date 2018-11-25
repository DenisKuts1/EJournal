package com.chnu.ejournal

import android.app.Application
import android.preference.PreferenceManager

class App : Application() {

    companion object {
        private const val NIGHT_NODE = "NIGHT_MODE"
        private const val NOTIFICATIONS = "NOTIFICATIONS"
        private const val LANGUAGE = "LANGUAGE"
        lateinit var instance: App
    }

    var isNightModeEnabled = false
        set(value) {
            field = value
            putBoolean(NIGHT_NODE, value)
        }
    var isNotificationsEnabled = false
        set(value) {
            field = value
            putBoolean(NOTIFICATIONS, value)
        }
    var language = "en"
        set(value) {
            field = value
            putString(LANGUAGE, value)
        }

    fun putBoolean(field: String, value: Boolean) {
        val pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val editor = pref.edit()
        editor.putBoolean(field, value)
        editor.apply()
    }

    fun putString(field: String, value: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val editor = pref.edit()
        editor.putString(field, value)
        editor.apply()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        isNightModeEnabled = pref.getBoolean(NIGHT_NODE, false)
        isNotificationsEnabled = pref.getBoolean(NOTIFICATIONS, false)
        language = pref.getString(LANGUAGE, "en") ?: "en"
    }
}