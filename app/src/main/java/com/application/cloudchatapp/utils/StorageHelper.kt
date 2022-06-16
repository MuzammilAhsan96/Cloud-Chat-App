package com.application.cloudchatapp.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Environment
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import java.io.File

object StorageHelper {

    private const val FOLDER_NAME="Professional Beauty"
    private const val IMAGE_FOLDER_NAME="Images"
    private const val IMAGE_FORMAT=".jpg"

    fun checkStoragePermissions(context: Context): Boolean {
        var ret = true
        if (SDK_INT >= Build.VERSION_CODES.R) {
            ret = Environment.isExternalStorageManager()
        }else if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            ret = false
        }
        return ret
    }
    fun requestStoragePermission(context: Context, onScopeResultLaucher: ActivityResultLauncher<Intent>, onPermissionlaucher: ActivityResultLauncher<Array<String>>) {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            var intent: Intent
            try {
                intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse(String.format("package:%s", context.applicationContext.packageName))


            } catch (e: Exception) {
                intent = Intent()
                intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
            }

            onScopeResultLaucher.launch(intent)
        }else{
            onPermissionlaucher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            )
        }
    }
    fun createFile(context: Context) : String {
        var path=""
        val capture_dir: String = context.externalCacheDir?.absolutePath + "/"+ FOLDER_NAME +"/"+ IMAGE_FOLDER_NAME +"/"
        val file = File(capture_dir)
        if (!file.exists()) {
            file.mkdirs()
        }
        path = capture_dir + System.currentTimeMillis() + IMAGE_FORMAT
       return path
    }
}