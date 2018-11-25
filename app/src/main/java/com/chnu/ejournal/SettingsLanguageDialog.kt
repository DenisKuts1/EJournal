package com.chnu.ejournal

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.ContextMenu
import android.view.View
import java.util.*

class SettingsLanguageDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.language_dialog, null)
        builder.setView(view)




                /*setTitle(R.string.language)
                .setItems(R.array.language_array){ dialog, which ->
                    when(which){
                        R.string.english -> {
                            val locale = Locale("en")
                            val config = activity!!.baseContext.resources.configuration
                            config.setLocale(locale)
                            activity!!.baseContext.resources.updateConfiguration(config, activity!!.baseContext.resources.displayMetrics)
                        }
                        R.string.ukrainian -> {
                            val locale = Locale("ua")
                            val config = activity!!.baseContext.resources.configuration
                            config.setLocale(locale)
                            activity!!.baseContext.resources.updateConfiguration(config, activity!!.baseContext.resources.displayMetrics)
                        }
                        R.string.russian -> {
                            val locale = Locale("ru")
                            val config = activity!!.baseContext.resources.configuration
                            config.setLocale(locale)
                            activity!!.baseContext.resources.updateConfiguration(config, activity!!.baseContext.resources.displayMetrics)
                        }
                    }
                }*/
        return builder.create()

    }
}