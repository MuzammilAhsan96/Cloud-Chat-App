package com.application.cloudchatapp.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.R
import com.application.cloudchatapp.base.BaseDialog
import com.application.cloudchatapp.databinding.DialogErrorBinding

class ErrorDialog : BaseDialog(), View.OnClickListener {

    private lateinit var ui: DialogErrorBinding

    override fun layoutRes(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return DialogErrorBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ui = binding as DialogErrorBinding
        initUI()
        initListeners()
    }


    private fun initUI() {
        ui.tvMessage.text = arguments?.getString("message")
    }

    private fun initListeners() {
        ui.btnOk.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOk -> {
                dismiss()
            }
        }
    }


}