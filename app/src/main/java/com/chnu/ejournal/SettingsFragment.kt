package com.chnu.ejournal

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "email"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SettingsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SettingsFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        setUpLanguageSettings(view)
        setUpNotificationsSettings(view)
        setUpNightModeSettings(view)

        return view
    }

    private fun setUpNightModeSettings(view: View) {
        val nightModeTextField = view.findViewById<TextView>(R.id.nightModeField)
        nightModeTextField.text = if (App.instance.isNightModeEnabled) {
            resources.getString(R.string.disable_dark_color_scheme)
        } else {
            resources.getString(R.string.enable_dark_color_scheme)
        }

        val nightModeSwitch = view.findViewById<Switch>(R.id.nightModeSwitch)
        if (App.instance.isNightModeEnabled) nightModeSwitch.isChecked = true
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                App.instance.isNightModeEnabled = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                App.instance.isNightModeEnabled = false
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            val intent = activity?.intent
            intent?.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            activity!!.finish()
            activity?.startActivity(intent)
        }
    }

    private fun setUpNotificationsSettings(view: View) {
        val notificationsSwitch = view.findViewById<Switch>(R.id.notificationsSwitch)
        if (App.instance.isNotificationsEnabled) notificationsSwitch.isChecked = true
        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                App.instance.isNotificationsEnabled = true
                //TODO enable notifications
            } else {
                App.instance.isNightModeEnabled = false
                //TODO disable notifications
            }
        }

        val notificationsTextField = view.findViewById<TextView>(R.id.notificationField)
        notificationsTextField.text = if (App.instance.isNotificationsEnabled) {
            resources.getString(R.string.do_not_receive_notifications)
        } else {
            resources.getString(R.string.receive_notifications)
        }
    }

    private fun setUpLanguageSettings(view: View) {
        val languageTextField = view.findViewById<TextView>(R.id.languageField)
        languageTextField.text = when (resources.configuration.locale.language) {
            "ua" -> resources.getString(R.string.ukrainian)
            "ru" -> resources.getString(R.string.russian)
            else -> resources.getString(R.string.english)
        }

        val languageButton = view.findViewById<LinearLayout>(R.id.settings_language_Layout)
        languageButton.setOnClickListener {
            AlertDialog.Builder(activity, R.style.LanguageSettingsDialog)
                    .setTitle(R.string.language)
                    .setItems(R.array.language_array) { dialog, which ->
                        val locale = when (which) {
                            0 -> Locale("en")
                            1 -> Locale("ua")
                            2 -> Locale("ru")
                            else -> Locale("en")
                        }
                        App.instance.language = when (which) {
                            0 -> "en"
                            1 -> "ua"
                            2 -> "ru"
                            else -> "en"
                        }
                        val config = activity!!.baseContext.resources.configuration
                        config.setLocale(locale)
                        activity!!.baseContext.resources.updateConfiguration(config, activity!!.baseContext.resources.displayMetrics)
                        val intent = activity?.intent
                        intent?.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        activity!!.finish()
                        activity?.startActivity(intent)
                    }.show()
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email: String) =
                SettingsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, email)
                    }
                }
    }
}
