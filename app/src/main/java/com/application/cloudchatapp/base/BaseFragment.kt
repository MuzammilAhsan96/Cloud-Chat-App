package com.application.cloudchatapp.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.utils.AppUtil
import com.application.cloudchatapp.R
import com.application.cloudchatapp.utils.NetworkChangeReceiver
import com.google.android.material.button.MaterialButton

abstract class BaseFragment : Fragment() {
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
        NetworkChangeReceiver(activity).observe(activity) {
            isOnline = it
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // method body
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                try {
                    /*if (binding is FragmentSectorQuestionnaireBinding || binding is FragmentConferenceBinding) {
                        requireActivity().finish()
                    } else {
                        Navigation.findNavController(view).popBackStack()
                        this.remove()
                    }*/
                } catch (e: Exception) {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
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

}