package com.application.cloudchatapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.base.BaseActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {
    protected lateinit var binding: ViewBinding
    protected var isOnline: Boolean = false
    protected lateinit var callback: OnBackPressedCallback
    open lateinit var activity: BaseActivity

    protected abstract fun layoutRes(inflater: LayoutInflater, container: ViewGroup?): ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = layoutRes(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}