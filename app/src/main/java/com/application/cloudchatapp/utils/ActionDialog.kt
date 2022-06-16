package com.application.cloudchatapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import com.application.cloudchatapp.databinding.DialogErrorBinding

interface DialogListener {
    fun onOkClick()
}

class ActionDialog(context: Context, var message: String?, private var listener: DialogListener) :
    Dialog(context, android.R.style.Theme_DeviceDefault_Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DialogErrorBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        val window = window
        val wlp = window?.attributes
        window?.attributes = wlp
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        binding.tvMessage.text = message

        binding.btnOk.setOnClickListener {
            listener.onOkClick()
            dismiss()
        }
    }
}