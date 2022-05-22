package com.example.applyhistory

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

object BindingUtil {

    @JvmStatic
    @BindingAdapter("statusName")
    fun statusName(view: TextView, status: Int?) {
        val statusArray = view.context.resources.getStringArray(R.array.apply_status)
        if (status != null) {
            view.text = statusArray[status]
        }
    }


    @JvmStatic
    @BindingAdapter("statusColor")
    fun statusColor(view: TextView, status: Int?) {

        if (status != null) {
            when(status){
                0 ->{
                    view.background.setTint(ContextCompat.getColor(view.context, R.color.applied_color))
                }

                1 ->{
                    view.background.setTint(ContextCompat.getColor(view.context, R.color.rejected_color))
                }
                2 ->{
                    view.background.setTint(ContextCompat.getColor(view.context, R.color.interview_color))
                }
                3 ->{
                    view.background.setTint(ContextCompat.getColor(view.context, R.color.accepted_color))
                }
            }
        }
    }

}