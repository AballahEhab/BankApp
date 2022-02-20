package com.abdallahehab.bankapp.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.isVisible(visible:Boolean){
    if(visible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}
@BindingAdapter("intText")
fun TextView.intText(value:Int){
    this.text = value.toString()
}