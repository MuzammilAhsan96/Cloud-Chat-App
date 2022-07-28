package com.application.cloudchatapp.base

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.utils.AppUtil
import com.application.cloudchatapp.R
import com.application.cloudchatapp.utils.AppConstant
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "BaseActivity"

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var binding: ViewBinding
    protected val simpleDateFormat =
        SimpleDateFormat(AppConstant.DATE_PATTERN.SIMPLE_DATE_FORMAT, Locale.getDefault())

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = layoutRes()
        setContentView(binding.root)
    }

    abstract fun layoutRes(): ViewBinding

    open fun showProgressBar() {
        showProgressBar(true)
    }

    open fun showProgressBar(isCancel: Boolean) {
        hideProgressBar()
        if (!isFinishing) {
            Log.d(TAG, "showProgressBar not finishing: $isFinishing")
            progressDialog = ProgressDialog.show(this@BaseActivity, "", "", true)
            if (progressDialog != null) {
                progressDialog?.setCanceledOnTouchOutside(isCancel)
                progressDialog?.setContentView(R.layout.progress_layout)
                Objects.requireNonNull(progressDialog?.window)
                    ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                Log.d(TAG, "test: showProgressBar: ")
            } else {
                Log.d(TAG, "showProgressBar: progress dialog null")
            }
        } else {
            Log.d(TAG, "showProgressBar: $isFinishing")
        }
    }


    open fun hideProgressBar() {
        Log.d(TAG, "test: hideProgressBar: ")
        if (!isFinishing) {
            if (progressDialog != null) {
                progressDialog?.dismiss()
                progressDialog = null
            }
        }
    }

    open fun launchActivity(classType: Class<out BaseActivity>) {
        startActivity(Intent(this, classType))
    }


    open fun launchActivity(classType: Class<out BaseActivity>, view: View) {
        AppUtil.preventTwoClick(view)
        startActivity(Intent(this, classType))
    }


    open fun launchActivityForResult(
        classType: Class<out BaseActivity>,
        bundle: Bundle,
        requestCode: Int
    ) {
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    open fun launchActivityForResult(
        classType: Class<out BaseActivity>,
        requestCode: Int,
        view: View?
    ) {
        AppUtil.preventTwoClick(view)
        val intent = Intent(this, classType)
        startActivityForResult(intent, requestCode)
    }

    open fun launchActivityForResult(
        classType: Class<out BaseActivity>,
        requestCode: Int,
        bundle: Bundle,
        view: View?
    ) {
        AppUtil.preventTwoClick(view)
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }


    open fun launchActivity(classType: Class<out BaseActivity>, bundle: Bundle) {
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    open fun launchActivityForResult(classType: Class<out BaseActivity>, requestCode: Int) {
        val intent = Intent(this, classType)
        startActivityForResult(intent, requestCode)
    }


    open fun launchActivity(bundle: Bundle, classType: Class<out BaseActivity>) {
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    open fun launchActivity(classType: Class<out BaseActivity>, bundle: Bundle, view: View) {
        AppUtil.preventTwoClick(view)
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    open fun hideSoftKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(
                currentFocus?.windowToken,
                InputMethodManager.RESULT_UNCHANGED_SHOWN
            )
        }
    }

    open fun addFragment(mFragment: BaseFragment, currentIndex: Int) {

    }

    open fun updateProgress(currentPos: Int) {

    }


    open fun onTabClick(pos: Int) {}




}