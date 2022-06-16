package com.application.cloudchatapp.extension

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.application.cloudchatapp.utils.AppUtil
import com.application.cloudchatapp.R
import com.application.cloudchatapp.base.App
import com.application.cloudchatapp.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random


fun View.gone() {
    this.visibility = View.GONE
}


fun View.invisible() {
    this.visibility = View.INVISIBLE
}


fun View.visible() {
    this.visibility = View.VISIBLE
}


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.invisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun View.preventTwoClick() {
    this.isEnabled = false
    this.postDelayed(Runnable { this.isEnabled = true }, 800)
}

fun Activity.setFullScreen() {
    this.window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        statusBarColor = Color.TRANSPARENT

    }
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

inline fun View.snack(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    snack(resources.getString(messageRes), length, f)
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    val snackBarView: View = snack.view
    snackBarView.setBackgroundColor(
        ContextCompat.getColor(
            snack.context,
            android.R.color.holo_red_light
        )
    )
    snack.f()
    snack.show()
}

inline fun View.internetConnection(
    isOnline: Boolean,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    var snack: Snackbar? = null
    if (isOnline) {
        snack = Snackbar.make(this, "Connected", length)
        snack?.view?.setBackgroundColor(
            ContextCompat.getColor(
                snack.context,
                android.R.color.holo_green_dark
            )
        )
        snack.f()
        snack.show()

    } else {
        snack = Snackbar.make(this, "Connecting...", length)
        snack?.view?.setBackgroundColor(
            ContextCompat.getColor(
                snack.context,
                android.R.color.black
            )
        )
    }
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}


fun Activity.getDP(statusBarHeight: Int): Int {
    return (statusBarHeight / (resources.displayMetrics.densityDpi / 160f)).roundToInt()
}

fun EditText.getString(): String {
    return this.text.trim().toString()
}

fun getColors(color: Int): Int {
    return ContextCompat.getColor(App.INSTANCE, color)
}

fun Float.roundToOneDecimalPlace(): Float {
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH)).apply {
        roundingMode = RoundingMode.HALF_UP
    }
    return df.format(this).toFloat()
}

fun <T> Context.launchActivity(context: Context, clazz: Class<T>) {
    val intent = Intent(context, clazz)
    startActivity(intent)
}

fun <T> Context.launchActivityFromFloating(context: Context, clazz: Class<T>) {
    val intent = Intent(context, clazz)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun <T> Context.launchActivityFromFloating(context: Context, clazz: Class<T>, callFrom: String?) {
    val intent = Intent(context, clazz)
    intent.putExtra(callFrom, callFrom)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun launchWebPage(context: Context, formUrl: String?) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(formUrl)
    ContextCompat.startActivity(context, intent, null)
}

fun <T> Context.launchActivityClearFlag(clazz: Class<T>) {
    val intent = Intent(this, clazz)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
}

fun <T> Context.launchActivityForResult(
    context: Activity,
    clazz: Class<T>,
    bundle: Bundle,
    requestCode: Int
) {
    val intent = Intent(context, clazz)
    intent.putExtras(bundle)
    ActivityCompat.startActivityForResult(context, intent, requestCode, bundle)
}

fun setStatusBarTransparentColor(activity: BaseActivity) {
    val window: Window = activity.window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = getColors(android.R.color.transparent)
}

fun setTransparentColor(activity: BaseActivity) {
    val window: Window = activity.window
    window.statusBarColor = getColors(android.R.color.transparent)
}

fun showToast(msg: String?, isCenter: Boolean? = false) {
    if (!TextUtils.isEmpty(msg)) {
        /*   val toast = Toast.makeText(
               App.INSTANCE,
               msg,
               Toast.LENGTH_SHORT
           ).apply { setGravity(Gravity.BOTTOM, 0, 100) }

           val toastView = toast.view
           toastView!!.setBackgroundResource(R.drawable.toast_drawable)
           toast.show()*/

        Toast.makeText(App.INSTANCE, msg, Toast.LENGTH_LONG).apply {
            if (isCenter == true)
                setGravity(Gravity.CENTER, 20, 20)
            view?.setBackgroundResource(R.drawable.toast_bg)
            (view?.findViewById<TextView>(android.R.id.message))?.setTextColor(AppUtil.getColor(R.color.white))
            show()
        }
    }
}

/*infix fun TextView.setTextValue(value: String?) {
    if (!TextUtils.isEmpty(value?.trim()) && value != null)
        this.text = value
    else
        this.text = App.INSTANCE.getString(R.string.na)
}

infix fun TextView.setNumberValue(value: String?) {
    if (!TextUtils.isEmpty(value?.trim()) && value != null && value != "0")
        this.text = value
    else
        this.text = App.INSTANCE.getString(R.string.na)
}*/


fun Fragment?.runOnUiThread(action: () -> Unit) {
    this ?: return
    if (!isAdded) return // Fragment not attached to an Activity
    activity?.runOnUiThread(action)
}

fun TextView.setCompoundDrawable(res: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(res, 0, 0, 0)
}

fun DrawerLayout.handleDrawerState() {
    if (!this.isDrawerOpen(GravityCompat.START)) {
        this.openDrawer(GravityCompat.START);
    } else {
        this.closeDrawer(GravityCompat.END);
    }
}
/*
fun Context.showMenu(view: View, array: ArrayList<String>, listener: PopupMenuCallback) {
    val popupMenu = ListPopupWindow(this)
    popupMenu.setAdapter(
        ArrayAdapter(
            this,
            R.layout.menu_layout,
            array
        )
    )
    popupMenu.anchorView = view
    popupMenu.height = ListPopupWindow.WRAP_CONTENT
    //popupMenu.horizontalOffset = -360
    // popupMenu.width = 100
    // popupMenu.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.stroke_round_corner_gray))

    //popupMenu.isModal = true
    popupMenu.setOnDismissListener {

    }

    popupMenu.setOnItemClickListener { adapterView, v, i, l ->
        listener.onItemSelect(array[i], i)
        popupMenu.dismiss()
    }

    popupMenu.show()
}*/

fun View.customPopupMenu(array: ArrayList<String>): PopupMenu {
    val menu =
        PopupMenu(context, this, Gravity.NO_GRAVITY, android.R.attr.actionOverflowMenuStyle, 0)

    array.forEachIndexed { index, s ->
        menu.menu.add(Menu.NONE, index, index, array[index])
    }
    menu.show()
    return menu
}

/*fun View.showMenu(array: ArrayList<String>, listener: PopupMenuCallback) {
    val popupMenu = ListPopupWindow(this.context)
    popupMenu.setAdapter(
        ArrayAdapter(
            this.context,
            R.layout.spinner_dropdown,
            array
        )
    )
    popupMenu.anchorView = this
    popupMenu.height = ListPopupWindow.WRAP_CONTENT

    popupMenu.setOnDismissListener {

    }

    popupMenu.setOnItemClickListener { adapterView, view, i, l ->
        listener.onItemSelect(array[i], i)
        popupMenu.dismiss()
    }

    popupMenu.show()
}*/

fun Context.getFilePathToSave(): String {
    val externalDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    var file: File? = null
    try {
        file = File(externalDir ?: "", "PBWorld/$timeStamp.pdf")
        file.createNewFile()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return Uri.fromFile(file).toString()
}

/*fun Activity.createFile(pickerInitialUri: Uri) {
   val CREATE_FILE = 1
   val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
       addCategory(Intent.CATEGORY_OPENABLE)
       type = "application/pdf"
       putExtra(Intent.EXTRA_TITLE, "invoice.pdf")

       // Optionally, specify a URI for the directory that should be opened in
       // the system file picker before your app creates the document.
       putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
   }
   startActivityForResult(intent, CREATE_FILE)
}*/

fun createFile(title: String, activityResultLauncher: ActivityResultLauncher<Intent>) {
    val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    intent.type = "text/html"
    intent.putExtra(Intent.EXTRA_TITLE, title)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.parse("/Documents"))
    }
    activityResultLauncher.launch(intent)
}

fun String.imageMultipart(): MultipartBody.Part {
    val file = File(this)
    val reqFile = file.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("image", file.name, reqFile)
}

fun String.requestBody(): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), this)
}

@Nullable
fun Uri?.getRealPathFromUri(@NonNull context: Context): String? {
    val contentResolver: ContentResolver = context.contentResolver ?: return null

    // Create file path inside app's data dir
    val filePath: String = (context.applicationInfo.dataDir.toString() + File.separator
            + System.currentTimeMillis())
    val file = File(filePath)
    try {
        val inputStream = contentResolver.openInputStream(this!!) ?: return null
        val outputStream: OutputStream = FileOutputStream(file)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()
    } catch (ignore: IOException) {
        return null
    }
    return file.absolutePath
}

fun View.randomColor(): Int {
    val mRandom: Random = Random(System.currentTimeMillis())
    // This is the base color which will be mixed with the generated one
    val baseColor = Color.WHITE
    val baseRed = Color.red(baseColor)
    val baseGreen = Color.green(baseColor)
    val baseBlue = Color.blue(baseColor)
    val red: Int = (baseRed + mRandom.nextInt(256)) / 2
    val green: Int = (baseGreen + mRandom.nextInt(256)) / 2
    val blue: Int = (baseBlue + mRandom.nextInt(256)) / 2
    return Color.rgb(red, green, blue)
}

fun String.plusWithSeparator(string: String?, separator: String? = ", "): String {
    return if (string != null && string.isNotEmpty())
        this.plus(separator).plus(string)
    else
        this
}

fun Any?.notNullAndEmpty(): Boolean {
    /********** this method is created to check if string is not null and empty (both conditions),
     *  kotlin's notNullOrEmpty() returns true if string is empty and not null
     *  but this method will return true only if string is not empty and not null ************/
    return if (this != null) {
        this is String && this.isNotEmpty()
    } else false
}