package com.application.cloudchatapp.extension

import android.os.CountDownTimer
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.application.cloudchatapp.utils.CustomTypefaceSpan
import com.application.cloudchatapp.R
import com.application.cloudchatapp.base.App


private var timer: CountDownTimer? = null
fun EditText.onTextChanged(listener: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            listener(s.toString())
        }
    })
}

fun EditText.onAfterChanged(listener: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            timer?.cancel()
            timer = null
            timer = object : CountDownTimer(500, 500) {
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    listener(s.toString())
                }

            }
            timer?.start()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}


fun EditText.text(): String {
    return text.toString().trim()
}

fun TextView.text(): String {
    return text.toString().trim()
}

fun EditText.isMinLength(minLength: Int?): Boolean {
    return this.text().length >= minLength ?: 0
}

fun EditText.disableCopyPaste() {
    isLongClickable = false
    setTextIsSelectable(false)
    customSelectionActionModeCallback = object : android.view.ActionMode.Callback {
        override fun onCreateActionMode(p0: android.view.ActionMode?, p1: Menu?): Boolean {
            return false
        }

        override fun onPrepareActionMode(p0: android.view.ActionMode?, p1: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(p0: android.view.ActionMode?, p1: MenuItem?): Boolean {
            return false
        }

        override fun onDestroyActionMode(p0: android.view.ActionMode?) {}

    }
}

fun TextView.spannableText(string: String?, start: Int? = 0, end: Int?) {
    val str = SpannableStringBuilder(string)
    val typeface = ResourcesCompat.getFont(App.INSTANCE, R.font.avenir_black)
    str.setSpan(
        CustomTypefaceSpan(typeface),
        start ?: 0,
        end ?: 0,
        Spanned.SPAN_EXCLUSIVE_INCLUSIVE
    )
    this.text = str
}