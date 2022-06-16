package com.application.cloudchatapp.ui.main

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var ui: ActivityMainBinding

    override fun layoutRes(): ViewBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = binding as ActivityMainBinding
    }


}