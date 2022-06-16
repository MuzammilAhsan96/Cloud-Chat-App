package com.application.cloudchatapp.callback

/**
 * Callback in all gpsAlert dialog and click on ok or cancel button callback
 */
interface IDialogCallback {
    fun onClick(isOk: Boolean)
}