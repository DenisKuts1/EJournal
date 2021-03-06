package com.chnu.ejournal.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.NumberPicker
import com.chnu.ejournal.App
import com.chnu.ejournal.MainActivity
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.Lab
import com.chnu.ejournal.entities.LabCreator
import com.chnu.ejournal.entities.Student
import com.chnu.ejournal.fragments.AppFragmentManager
import com.chnu.ejournal.fragments.labs.LabsFragment
import com.chnu.ejournal.retrofit.MyRetrofitApi
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception
import kotlin.math.roundToInt

class AddPointDialog: DialogFragment() {

    lateinit var lab: Lab
    lateinit var student: Student
    lateinit var labsFragment: LabsFragment
    lateinit var appFragmentManager: AppFragmentManager
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.add_point_dialog, null)
        val numberPicker = view.findViewById<NumberPicker>(R.id.number_picker)
        numberPicker.minValue = 0
        numberPicker.maxValue = lab.maxMark.roundToInt()

        builder.setView(view)
        builder.setPositiveButton("OK"){ dialog, id ->
            Observable.create<String> {
                subscriber ->
                try {
                    MyRetrofitApi.postPoint(student.id, lab.id, numberPicker.value.toDouble())

                } catch (e: Exception){
                    //e.printStackTrace()
                }
                subscriber.onNext("")
                subscriber.onCompleted()
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { retrievedNews ->
                                println(1)
                                lab.points[student] = numberPicker.value
                                println(2)
                                labsFragment.setUp()
                                println(3)
                                //ppFragmentManager.onBackPressed()
                                println(4)
                            },
                            { e ->
                                e.printStackTrace()
                            }
                    )
        }.setNegativeButton("Cancel") { dialog, id ->
            this.dialog.cancel()
        }
        return builder.create()
    }
}