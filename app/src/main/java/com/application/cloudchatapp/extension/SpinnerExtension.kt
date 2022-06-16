package com.application.cloudchatapp.extension

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

fun Spinner.onSelected(listner: (AdapterView<*>?,Int) -> Unit){
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            listner(parent,position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }
}