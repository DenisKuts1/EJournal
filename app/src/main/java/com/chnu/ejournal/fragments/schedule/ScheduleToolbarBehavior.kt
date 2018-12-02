package com.chnu.ejournal.fragments.schedule

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

class ScheduleToolbarBehavior : CoordinatorLayout.Behavior<RelativeLayout> {

    var finalYPosition = 0.0f
    lateinit var context: Context

    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.context = context
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: RelativeLayout, dependency: View): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: RelativeLayout, dependency: View): Boolean {

        println("${dependency.x} ${dependency.y}: ${child.x} ${child.y}")

        return true
    }

    fun getStatusBarHeight(): Int {
        val resourceId = context.resources.getIdentifier("schedule_status_bar_height", "dimen", "android")
        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
    }

}