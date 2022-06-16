package com.application.cloudchatapp.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.utils.AppUtil

abstract class BaseDialog : DialogFragment() {
    protected lateinit var binding: ViewBinding
    open lateinit var activity: BaseActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = layoutRes(inflater, container)
        dialog?.setCanceledOnTouchOutside(true)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as BaseActivity
    }


    fun launchActivity(classType: Class<out BaseActivity?>?, bundle: Bundle?, requestCode: Int) {
        val intent = Intent(activity, classType)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    fun launchActivity(classType: Class<out BaseActivity?>?, requestCode: Int) {
        val intent = Intent(activity, classType)
        startActivityForResult(intent, requestCode)
    }

    fun launchActivity(classType: Class<out BaseActivity?>?, view: View?) {
        AppUtil.preventTwoClick(view)
        startActivity(Intent(activity, classType))
    }
/*
    open fun launchActivity(classType: Class<out BaseActivity>, bundle: Bundle) {
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivity(intent)
    }*/

    protected abstract fun layoutRes(inflater: LayoutInflater, container: ViewGroup?): ViewBinding
}