package com.application.cloudchatapp.utils

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import com.application.cloudchatapp.R
import com.application.cloudchatapp.base.BaseActivity

object DialogUtils {
    fun showAlertAppSetting(
        activity: BaseActivity,
        permissionMsg: String
    ) {
        val dialog = android.app.AlertDialog.Builder(activity)
            .setTitle(activity.getString(R.string.app_name))
            .setMessage(permissionMsg)
            .setPositiveButton(activity.getString(R.string.yes)) { dialog1: DialogInterface?, which: Int ->
                launchSetting(activity)
                dialog1?.dismiss()
            }
            .setNegativeButton(activity.getString(R.string.no)) { dialog12: DialogInterface?, which: Int ->
                dialog12?.dismiss()
            }.show()
        dialog.setCanceledOnTouchOutside(false)
        val positiveButton = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
        val negativeButton = dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE)
        positiveButton.setTextColor(AppUtil.getColor(R.color.black))
        negativeButton.setTextColor(AppUtil.getColor(R.color.black))
    }

    fun launchSetting(activity: BaseActivity) {
        val intent = Intent("android.settings.APPLICATION_DETAILS_SETTINGS")
        val uri = Uri.fromParts("package", activity.packageName, null as String?)
        intent.data = uri
        activity.startActivityForResult(intent, AppConstant.REQUEST_CODE.APP_SETTING)
    }


}