package com.application.cloudchatapp.utils

import android.app.NotificationManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.icu.util.TimeZone
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.application.cloudchatapp.base.App
import com.application.cloudchatapp.extension.enable
import com.application.cloudchatapp.extension.visible
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

private const val TAG = "AppUtil"

object AppUtil {

    fun String.capitalise() = replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }

    fun isConnection(): Boolean {
        val connectivityManager = App.INSTANCE
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo =
            Objects.requireNonNull(connectivityManager)
                .activeNetworkInfo
        val isNetwork =
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        return isNetwork
    }


    /*fun opts(): IO.Options? {
        val opts: IO.Options = IO.Options()
        opts.reconnection = true
        opts.transports = arrayOf(Polling.NAME)
        return opts
    }
*/

    fun isOnline(context: Context?): Boolean {
        return try {
            val cm =
                App.INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }

    fun preventTwoClick(view: View?) {
        if (view != null) {
            view.isEnabled = false
            view.postDelayed(Runnable { view.isEnabled = true }, 800)
        }
    }

    /*fun loadTermSpannable(context: Context?): Spannable {
        val wordtoSpan: Spannable = SpannableString(context?.getString(R.string.term_condition))

        // Terms and Conditions
        wordtoSpan.setSpan(object : ClickableSpan() {
            override fun onClick(vi: View) {
                context?.startActivity(
                    Intent(context, WebviewActivity::class.java).putExtra(
                        "url",
                        "https://professionalbeauty.co.uk/site/termsandconditions"
                    )
                )
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds); }
        }, 39, 58, 0)


        // Privacy Policy
        wordtoSpan?.length?.let {
            wordtoSpan.setSpan(object : ClickableSpan() {
                override fun onClick(vi: View) {
                    context?.startActivity(
                        Intent(context, WebviewActivity::class.java).putExtra(
                            "url",
                            "https://professionalbeauty.co.uk/site/privacypolicyandcookies"
                        )
                    )
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                }
            }, 63, it, 0)
        }

        return wordtoSpan
    }

*/
    fun buttonLoader(show: Boolean, button: Button, progressBar: ProgressBar) {
        if (show) {
            button.tag = button.text
            button.text = ""
        } else {
            button.text = button.tag as String

        }

        button.enable(!show)
        progressBar.visible(show)

    }

    fun generateFCMToken(context: Context?): String? {
        var fcmToken: String? = null
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                generateFCMToken(context)
                return@OnCompleteListener
            }

            fcmToken = task.result
            PreferenceKeeper.getInstance().deviceToken = fcmToken
            Log.d("FFFFFF", "generated fcm token: $fcmToken")
            PreferenceKeeper.getInstance().deviceId = Settings.Secure.getString(
                context?.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        })
        return fcmToken
    }


    /*fun updateProgress(
        activity: BaseActivity,
        index: Int,
        questionList: MutableList<QuestionData?>?
    ) {
        val rvStep =
            activity?.findViewById<ConstraintLayout>(R.id.clTab)?.findViewById<RecyclerView>(
                R.id.rvStep
            )
        rvStep?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvStep?.adapter = QuestionnaireStepAdapter(activity, questionList)

        android.os.Handler(Looper.getMainLooper()).postDelayed(Runnable {
            rvStep?.smoothScrollToPosition(index)
        }, 100)

    }*/

    /*fun showLoader(activity: BaseActivity, show: Boolean) {
        activity.findViewById<ProgressBar>(R.id.pbLoader)?.visible(show)
    }*/

    fun watchYoutubeVideo(context: Context, id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$id"))
        try {
            context.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(webIntent)
        }
    }

    fun getColor(color: Int): Int {
        return ContextCompat.getColor(App.INSTANCE, color)
    }

    fun setBg(view: View?, id: Int) {
        view?.setBackgroundResource(id)
    }

    fun setBgColor(view: View?, id: Int) {
        view?.setBackgroundColor(id)
    }

    fun setBgTint(view: View?, color: Int) {
        view?.let {
            ViewCompat.setBackgroundTintList(
                it,
                ColorStateList.valueOf(color)
            )
        }
    }

    fun setTint(view: AppCompatImageView?, color: Int) {
        view?.setColorFilter(color)
    }

    /*fun getUserProfile(): UserProfileData {
        var user = AppConstant.USER_PROFILE
        if (user.firstName == null) {
            user = PreferenceKeeper.getInstance().getUser() ?: UserProfileData()
            AppConstant.USER_PROFILE = user
        }
        return user
    }*/

    fun getFullName(firstName: String?, lastName: String?): String {
        var name1 = "NA"
        var name2: String? = ""
        if (!TextUtils.isEmpty(firstName)) {
            name1 = "$firstName "
        }
        if (!TextUtils.isEmpty(lastName)) {
            name2 = lastName
        }
        return name1 + "" + name2
    }

    fun getFileDir(): String? {
        return App.INSTANCE.getExternalFilesDir("pbw")?.absolutePath
    }

    fun getExternalFileDir(context: Context?): String? {
        val file: File? =
//            File(Environment.getExternalStorageDirectory().toString() + "/PBWorld")
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        if (file?.exists() == false) {
            val res = file.mkdirs()
        }
        val path = file?.absolutePath
        Log.i(TAG, "Path: $path")
        return path
    }

    fun multiPermissionDenialDialog(context: Context?, title: String?, message: String?) {
        var dialogMultiplePermissionsListener: MultiplePermissionsListener =
            DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(context)
                .withTitle(title)
                .withMessage(message)
                .withButtonText(android.R.string.ok)
                .build()
    }

   /* fun getFinalQuestion(data: MutableList<QuestionData?>?): MutableList<QuestionData?>? {

        val TYPE_COUNTY = 4
        val TYPE_CITY = 5

        var ddd: MutableList<QuestionData?>? = ArrayList()

        data?.let { it ->
            var name1 = ""
            var id1 = ""

            val city = it.filter { it?.staticQuestionType == TYPE_CITY }.toMutableList()
            if (city.size > 0) {
                name1 = city[0]?.question ?: ""
                id1 = city[0]?._id ?: ""
            }

            if (!TextUtils.isEmpty(name1)) {
                data.forEach { q ->
                    q?.let {
                        if (q.staticQuestionType != TYPE_CITY) {
                            if (q.staticQuestionType == TYPE_COUNTY) {
                                q.questionCity = name1
                                q.cityId = id1
                            }
                            ddd?.add(q)
                        }
                    }
                }
            } else {
                ddd = data
            }
        }
        return ddd
    }
*/
//    //TODO exposed api
//    fun placeApi(context: Context?): PlaceAPI {
//        return PlaceAPI.Builder().apiKey(context?.getString(R.string.google_places_key)!!)
//            .build(context)
//    }

    fun getAddress(context: Context?, latitude: Double, longitude: Double, type: String): String {
        val addresses: List<Address>
        val geocoder = Geocoder(context, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            latitude,
            longitude,
            1
        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5


        return when (type) {
            AppConstant.LOCATION.ADDRESS -> addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            AppConstant.LOCATION.CITY -> addresses[0].locality
            AppConstant.LOCATION.STATE -> addresses[0].adminArea
            AppConstant.LOCATION.COUNTRY -> addresses[0].countryName
            AppConstant.LOCATION.POSTAL_CODE -> addresses[0].postalCode
            AppConstant.LOCATION.KNOWN_NAME -> addresses[0].featureName
            else -> addresses[0].getAddressLine(0)
        }
    }

    fun displayTimeZone(tz: TimeZone): String? {
        val hours: Long = TimeUnit.MILLISECONDS.toHours(tz.rawOffset.toLong())
        var minutes: Long = (TimeUnit.MILLISECONDS.toMinutes(tz.rawOffset.toLong())
                - TimeUnit.HOURS.toMinutes(hours))
        // avoid -4:-30 issue
        minutes = abs(minutes)
        var result: String? = ""
        result = if (hours > 0) {
            java.lang.String.format("(GMT+%d:%02d) %s", hours, minutes, tz.id)
        } else {
            java.lang.String.format("(GMT%d:%02d) %s", hours, minutes, tz.id)
        }
        return result
    }


    fun getCurrentDate(): String? {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.format(Date())
    }

    fun getCurrentTime(): String? {
        val sdf = SimpleDateFormat("hh:mma")
        return sdf.format(Date())
    }

    fun getCurrentTimeAdjusted(): String? {
        val sdf = SimpleDateFormat("hh:mma")
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, 60)
        return sdf.format(calendar.time)
        }

    fun clearAllNotifications() {
        val nMgr: NotificationManager? = App.INSTANCE.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nMgr?.cancelAll();
    }
}