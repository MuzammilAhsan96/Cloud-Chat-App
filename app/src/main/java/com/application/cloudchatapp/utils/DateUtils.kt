package com.app.pbgevents.utils

import android.util.Log
import com.application.cloudchatapp.utils.AppConstant
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "DateUtils"

object DateUtils {

    /**
    Date and Time format explanation
    EEE : Day ( Mon )
    MMMM : Full month name ( December ) // MMMM February
    MMM : Month in words ( Dec )
    MM : Month ( 12 )
    dd : Day in 2 chars ( 03 )
    d: Day in 1 char (3)
    HH : Hours ( 12 )
    mm : Minutes ( 50 )
    ss : Seconds ( 34 )
    yyyy: Year ( 2020 ) //both yyyy and YYYY are same
    YYYY: Year ( 2020 )
    zzz : GMT+05:30
    a : ( AM / PM )
    aa : ( AM / PM )
    aaa : ( AM / PM )
    aaaa : ( AM / PM )
     */

    const val DATE_FORMAT_1 = "hh:mm a"
    const val DATE_FORMAT_2 = "h:mm a"
    const val DATE_FORMAT_3 = "yyyy-MM-dd"
    const val DATE_FORMAT_4 = "dd-MMMM-yyyy"
    const val DATE_FORMAT_5 = "dd MMMM yyyy"
    const val DATE_FORMAT_6 = "dd MMMM yyyy zzzz"
    const val DATE_FORMAT_7 = "EEE, MMM d, ''yy"
    const val DATE_FORMAT_8 = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_9 = "h:mm a dd MMMM yyyy"
    const val DATE_FORMAT_10 = "K:mm a, z"
    const val DATE_FORMAT_11 = "hh 'o''clock' a, zzzz"
    const val DATE_FORMAT_12 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT_13 = "E, dd MMM yyyy HH:mm:ss z"
    const val DATE_FORMAT_14 = "yyyy.MM.dd G 'at' HH:mm:ss z"
    const val DATE_FORMAT_15 = "yyyyy.MMMMM.dd GGG hh:mm aaa"
    const val DATE_FORMAT_16 = "EEE, d MMM yyyy HH:mm:ss Z"
    const val DATE_FORMAT_17 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    const val DATE_FORMAT_18 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    const val DATE_FORMAT_19 = "dd MMM, yyyy"
    const val DATE_FORMAT_20 = "EEEE"
    const val DATE_FORMAT_21 = "dd"

    /**
     * @param time in milliseconds (Timestamp)
     * @param mDateFormat SimpleDateFormat
     */
    fun getDateTimeFromTimeStamp(time: Long?, mDateFormat: String?): String? {
        val dateFormat = SimpleDateFormat(mDateFormat)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = Date(time!!)
        return dateFormat.format(dateTime)
    }

    /**
     * Get Timestamp from date and time
     *
     * @param mDateTime datetime String
     * @param mDateFormat Date Format
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun getTimeStampFromDateTime(mDateTime: String?, mDateFormat: String?): Long {
        val dateFormat = SimpleDateFormat(mDateFormat)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = dateFormat.parse(mDateTime)
        return date.time
    }

    /**
     * Return  datetime String from date object
     *
     * @param mDateFormat format of date
     * @param date date object that you want to parse
     */
    /* fun formatDateTimeFromDate(mDateFormat: String?, date: Date?): String? {
         return if (date == null) {
             null
         } else DateFormat.format(mDateFormat, date).toString()
     }
 */
    /**
     * Convert one date format string  to another date format string in android
     *
     * @param inputDateFormat Input SimpleDateFormat
     * @param outputDateFormat Output SimpleDateFormat
     * @param inputDate input Date String
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun formatDateFromDateString(
        outputDateFormat: String?,
        inputDate: String?
    ): String? {
        val mParsedDate: Date
        val mOutputDateString: String
        val mInputDateFormat =
            if (inputDate?.contains("+") == true) SimpleDateFormat(
                AppConstant.DATE_PATTERN.API_DATE_FORMAT,
                Locale.getDefault()
            ) else SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        mParsedDate = mInputDateFormat.parse(inputDate)
        mOutputDateString = mOutputDateFormat.format(mParsedDate)
        return mOutputDateString
    }


    public fun calculateDate(date: String?): String {
        if (date != null) {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            try {
                val date: Date = sdf.parse(date)
                return SimpleDateFormat("dd MMM. yyyy").format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        return ""
    }


    fun calculateEventDate(startDate: String?, endDate: String?, timeZone: String?): String {
        var returnDate = "NA"

        if (startDate != null) {
            var startDateFormat: SimpleDateFormat? = null
            var endDateFormat: SimpleDateFormat? = null
            startDateFormat =
                if (startDate.contains("+")) SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ssZZZZZ",
                    Locale.getDefault()
                )
                else SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            startDateFormat.timeZone = TimeZone.getTimeZone(timeZone)
            val string = "Thu Sep 02 2021 20:29:42 GMT+0530"
            val sdf = "E MMM dd yyyy HH:mm:ss Z"


            endDateFormat =
                if (endDate?.contains("+") == true) SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ssZZZZZ",
                    Locale.getDefault()
                )
                else SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            endDateFormat.timeZone = TimeZone.getTimeZone(timeZone)


            val convertedFormat = SimpleDateFormat("dd MMM. yyyy", Locale.getDefault())
            val onlyStartDate = SimpleDateFormat("dd", Locale.getDefault())
            val onlyMonthFormat = SimpleDateFormat("MMM", Locale.getDefault())
            try {
                val startDateConverted: Date? = startDateFormat.parse(startDate)
                val endDateConverted: Date? = endDateFormat.parse(endDate ?: "")
                convertedFormat.apply {
                    val convertedStartDate = format(startDateConverted ?: "")
                    val convertedEndDate = format(endDateConverted ?: "")
                    val onlyDate = onlyStartDate.format(startDateConverted ?: "")
                    val onlyStartMonth = onlyMonthFormat.format(startDateConverted ?: "")
                    val onlyEndMonth = onlyMonthFormat.format(endDateConverted ?: "")
                    if (convertedStartDate.equals(convertedEndDate)) {
                        returnDate = convertedStartDate
                    } else if (onlyStartMonth.equals(onlyEndMonth)) {
                        returnDate = "$onlyDate-$convertedEndDate"
                    } else {
                        returnDate = "$onlyDate $onlyStartMonth-$convertedEndDate"
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        return returnDate
    }

    fun calculateConferenceDate(startDate: String?, endDate: String?, timeZone: String?): String {
        var returnDate = "NA"

        if (startDate != null) {
            var startDateFormat: SimpleDateFormat? = null
            var endDateFormat: SimpleDateFormat? = null
            startDateFormat =
                if (startDate.contains("+")) SimpleDateFormat(
                    "E MMM dd yyyy HH:mm:ss ZZZZZ",
                    Locale.getDefault()
                )
                else SimpleDateFormat("E MMM dd yyyy HH:mm:ss Z", Locale.getDefault())
            startDateFormat.timeZone = TimeZone.getTimeZone(timeZone)
            val string = "Thu Sep 02 2021 20:29:42 GMT+0530"
            val sdf = "E MMM dd yyyy HH:mm:ss Z"


            endDateFormat =
                if (endDate?.contains("+") == true) SimpleDateFormat(
                    "E MMM dd yyyy HH:mm:ss ZZZZZ",
                    Locale.getDefault()
                )
                else SimpleDateFormat("E MMM dd yyyy HH:mm:ss Z", Locale.getDefault())
            endDateFormat.timeZone = TimeZone.getTimeZone(timeZone)


            val convertedFormat = SimpleDateFormat("dd MMM. yyyy", Locale.getDefault())
            val onlyStartDate = SimpleDateFormat("dd", Locale.getDefault())
            val onlyMonthFormat = SimpleDateFormat("MMM", Locale.getDefault())
            try {
                val startDateConverted: Date? = startDateFormat.parse(startDate)
                val endDateConverted: Date? = endDateFormat.parse(endDate ?: "")
                convertedFormat.apply {
                    val convertedStartDate = format(startDateConverted ?: "")
                    val convertedEndDate = format(endDateConverted ?: "")
                    val onlyDate = onlyStartDate.format(startDateConverted ?: "")
                    val onlyStartMonth = onlyMonthFormat.format(startDateConverted ?: "")
                    val onlyEndMonth = onlyMonthFormat.format(endDateConverted ?: "")
                    if (convertedStartDate.equals(convertedEndDate)) {
                        returnDate = convertedStartDate
                    } else if (onlyStartMonth.equals(onlyEndMonth)) {
                        returnDate = "$onlyDate-$convertedEndDate"
                    } else {
                        returnDate = "$onlyDate $onlyStartMonth-$convertedEndDate"
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        return returnDate
    }


    fun calculateDateFullTimeStamp(date: String?, timeZone: String?): String {
        if (date != null) {
            val sdf =
                if (date?.contains("+") == true) SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
                else SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            sdf.timeZone = TimeZone.getTimeZone(timeZone)
            try {
                val date: Date = sdf.parse(date)
                return SimpleDateFormat("MMM dd, hh:mm a").format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        return ""
    }


    fun getTimeZone(date: String?, timeZone: String?): String {
        if (date != null) {
            var sdf = if (date.contains("+")) SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
            else SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

            sdf.timeZone = TimeZone.getTimeZone(timeZone)
            try {
                val date: Date = sdf.parse(date)
                return SimpleDateFormat("zzz").format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        return ""
    }

    fun getTimeZoneConference(date: String?): String {
        if (date != null) {
            val sdf = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzzz", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            try {
//                val date: Date = sdf.parse(date)
//                return SimpleDateFormat("z",Locale.getDefault()).format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }



        return "IST"
    }


    fun calculatStageDate(startDate: String?, endDate: String?, timeZone: String?): String {
        var date1: Date? = null
        val formatter = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzzz")
        var returnDate = ""
        if (startDate != null) {
            var startDateFormat: SimpleDateFormat? = null
            var endDateFormat: SimpleDateFormat? = null
            startDateFormat = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzzz")
            startDateFormat.timeZone = TimeZone.getTimeZone(timeZone)

            endDateFormat = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzzz")
            endDateFormat.timeZone = TimeZone.getTimeZone(timeZone)


            val convertedFormat = SimpleDateFormat("dd MMM. yyyy")
            val onlyStartDate = SimpleDateFormat("dd")
            val onlyMonthFormat = SimpleDateFormat("MMM")
            try {
                val startDateConverted: Date? = startDateFormat?.parse(startDate)
                val endDateConverted: Date? = endDateFormat?.parse(endDate)
                convertedFormat.apply {
                    val convertedStartDate = format(startDateConverted)
                    val convertedEndDate = format(endDateConverted)
                    val onlyDate = onlyStartDate.format(startDateConverted)
                    val onlyStartMonth = onlyMonthFormat.format(startDateConverted)
                    val onlyEndMonth = onlyMonthFormat.format(endDateConverted)
                    if (convertedStartDate.equals(convertedEndDate)) {
                        returnDate = convertedStartDate
                    } else if (onlyStartMonth.equals(onlyEndMonth)) {
                        returnDate = "$onlyDate-$convertedEndDate"
                    } else {
                        returnDate = "$onlyDate $onlyStartMonth-$convertedEndDate"
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        return returnDate
    }


    fun calculateDateFullTimeStampConferrence(date: String?): String {
        if (date != null) {
            val sdf = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzzz", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            try {
                val date: Date = sdf.parse(date)
                return SimpleDateFormat("MMM dd, hh:mm a").format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        return ""
    }


    fun calculateTime(date: Date, timeZone: String?): String {
        if (date != null) {
            try {
                val formatNew = SimpleDateFormat("hh:mm a")
                formatNew.timeZone = TimeZone.getTimeZone(timeZone)
                return formatNew.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return ""
    }

    fun calculateFullTime(date: Date, timeZone: String?): String {
        if (date != null) {
            try {
                val formatNew = SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_IN_WORDS)
                formatNew.timeZone = TimeZone.getTimeZone(timeZone)
                return formatNew.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return ""
    }


    fun returnDate(date: String?, timeZone: String?): Date? {
        var returnDate: Date? = null
        if (date != null) {
            val sdf = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzzz")
            sdf.timeZone = TimeZone.getTimeZone(timeZone)
            try {
                returnDate = sdf.parse(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return returnDate
    }

    fun getDateForTableView(d: String?): String? {
        val outputDate: DateFormat = SimpleDateFormat("dd", Locale.getDefault())
        val outputMonth: DateFormat = SimpleDateFormat("MM", Locale.getDefault())
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        var inputText = d
        if (inputText != null) {
            val date: Date = inputFormat.parse(inputText) ?: Date()
            val outputDateText: String = outputDate.format(date)
            val outputMonthText: String = outputMonth.format(date)
            inputText =
                outputDateText.plus("\n").plus(getMonthForTableView(outputMonthText.toInt()))
        }
        return inputText
    }

    fun getLongDateForTableView(d: Long?): String? {
        var outputtext = ""
        val outputDate: DateFormat = SimpleDateFormat("dd", Locale.getDefault())
        val outputMonth: DateFormat = SimpleDateFormat("MM", Locale.getDefault())
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        var inputText = d
        if (inputText != null) {
            val date: Date = Date(d ?: 0)
            val outputDateText: String = outputDate.format(date)
            val outputMonthText: String = outputMonth.format(date)
            outputtext =
                outputDateText.plus("\n").plus(getMonthForTableView(outputMonthText.toInt()))
        }
        return outputtext
    }

    fun getDateForSorting(d: String?): String? {
        val outputDate: DateFormat = SimpleDateFormat("dd", Locale.getDefault())
        val outputMonth: DateFormat = SimpleDateFormat("MM", Locale.getDefault())
        val outputYear: DateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        var inputText = d
        if (inputText != null) {
            val date: Date = inputFormat.parse(inputText) ?: Date()
            val outputDateText: String = outputDate.format(date)
            val outputMonthText: String = outputMonth.format(date)
            val outputYearText: String = outputYear.format(date)
            inputText =
                outputDateText.plus(" ").plus(outputMonthText).plus(" ").plus(outputYearText)
        }
        return inputText
    }


    fun getLongDateForSorting(d: Long?): String? {
        var outputtext = ""
        val outputDate: DateFormat = SimpleDateFormat("dd", Locale.getDefault())
        val outputMonth: DateFormat = SimpleDateFormat("MM", Locale.getDefault())
        val outputYear: DateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        var inputText = d
        if (inputText != null) {
            val date: Date = Date(d ?: 0)
            val outputDateText: String = outputDate.format(date)
            val outputMonthText: String = outputMonth.format(date)
            val outputYearText: String = outputYear.format(date)
            outputtext =
                outputDateText.plus(" ").plus(outputMonthText).plus(" ").plus(outputYearText)
        }
        return outputtext
    }

    private fun getMonthForTableView(m: Int): String {
        return when (m) {
            1 -> "Jan"
            2 -> "Feb"
            3 -> "Mar"
            4 -> "Apr"
            5 -> "May"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Aug"
            9 -> "Sep"
            10 -> "Oct"
            11 -> "Nov"
            12 -> "Dec"
            else -> ""
        }
    }

    fun getDateFromApiDate(d: String?): String? {
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        val outputDate: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.NORMAL_DATE_FORMAT, Locale.getDefault())


        var inputText = d
        if (inputText != null) {
            val date: Date = inputFormat.parse(inputText) ?: Date()
            inputText = outputDate.format(date)
        }
        return inputText
    }


    fun getSimpleDateFromApiDate(d: String?): String? {
        Log.d(TAG, "getApiDateFromDate:  before$d")
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())
        val outputDate: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_SIMPLE, Locale.getDefault())

        var inputText = d
        if (inputText != null) {
            val date: Date = inputFormat.parse(inputText) ?: Date()
            inputText = outputDate.format(date)
        }

        Log.d(TAG, "getApiDateFromDate: after $inputText")
        return inputText
    }

    fun getSimpleDateFromApiDate2(d: String?): String? {
        Log.d(TAG, "getApiDateFromDate:  before$d")
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.RESCHEDULE_DATE_FORMAT, Locale.getDefault())
        val outputDate: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_SIMPLE, Locale.getDefault())

        var inputText = d
        if (inputText != null) {

            val date: Date = inputFormat.parse(inputText) ?: Date()
            inputText = outputDate.format(date)
        }

        Log.d(TAG, "getApiDateFromDate: after $inputText")
        return inputText
    }

    fun getApiDateFromDate(d: String?): String? {
        Log.d(TAG, "getApiDateFromDate:  before$d")
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.NORMAL_DATE_FORMAT, Locale.getDefault())
        val outputDate: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT2, Locale.getDefault())

        var inputText = d
        if (inputText != null) {
            val date: Date = inputFormat.parse(inputText) ?: Date()
            inputText = outputDate.format(date)
        }

        Log.d(TAG, "getApiDateFromDate: after $inputText")
        return inputText
    }

    fun getTimeInLocalTimeZone(inputDate: String?): String? {
        var formattedDate: String? = null
        val originalFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT)
        originalFormat.timeZone = TimeZone.getTimeZone("GMT")

        val targetFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.TIME_FORMAT, Locale.getDefault())
        val date: Date?
        try {
            date = originalFormat.parse(inputDate ?: "")
            if (date != null) formattedDate = targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }


    fun getDateInLocalTimeZone(inputDate: String?): String? {
        var formattedDate: String? = null
        val originalFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT)
        originalFormat.timeZone = TimeZone.getTimeZone("GMT")

        val targetFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.NORMAL_DATE_FORMAT, Locale.getDefault())
        val date: Date?
        try {
            date = originalFormat.parse(inputDate ?: "")
            if (date != null) formattedDate = targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun getTime(d: String?): String? {
        val outputDate: DateFormat = SimpleDateFormat("HH", Locale.getDefault())
        val outputMonth: DateFormat = SimpleDateFormat("mm", Locale.getDefault())
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        var inputText = d
        if (inputText != null) {
            val date: Date = inputFormat.parse(inputText) ?: Date()
            var outputDateText: String = outputDate.format(date)
            var outputMonthText: String = outputMonth.format(date)
            if (outputDateText.toInt() > 12) {
                outputDateText = (outputDateText.toInt() - 12).toString()
                if (outputDateText.toInt() < 10)
                    outputDateText = "0".plus(outputDateText)
                inputText = outputDateText.plus(":").plus(outputMonthText).plus(":PM")
            } else {
                inputText = outputDateText.plus(":").plus(outputMonthText).plus(":AM")
            }
        }
        return inputText
    }


    fun getCorrectTime(d: String?): String? {
        var outputDateText: String = ""
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        val outputDate: DateFormat = SimpleDateFormat("hh:mma", Locale.getDefault())


        var inputText = d
        if (inputText != null) {
            val date: Date = inputFormat.parse(inputText) ?: Date()
            outputDateText = outputDate.format(date)
        }
        return outputDateText
    }
    fun getCorrectTime2(d: String?): String? {
        Log.d(TAG, "getCorrectTime2: "+d)
        var outputDateText: String = ""
        var inputFormat:DateFormat? = null
        var outputDate: DateFormat? = null
        var date:Date? = null
        var inputText = d

        try {
             inputFormat = SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT2, Locale.getDefault())
            date = inputFormat?.parse(inputText) ?: Date()
        } catch(e:java.lang.Exception) {
            Log.d(TAG, "getCorrectTime2 catch: "+e.toString())
             inputFormat = SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())
            date = inputFormat?.parse(inputText) ?: Date()
        }

        try {
            outputDate = SimpleDateFormat("hh:mma", Locale.getDefault())
        } catch (e:java.lang.Exception){
            Log.d(TAG, "getCorrectTime2: "+e.toString())
        }



            date?.let {
                outputDateText = outputDate?.format(date) ?: ""

        }
        return outputDateText
    }


    //Function will return list of date in (dd MM yyyy) format
    fun getSortedDateList(dateList: MutableList<String?>?): MutableList<String?>? {
        if (dateList != null) {
            for (i in 0 until dateList.size.minus(1)) {
                for (j in 0 until dateList.size.minus(1).minus(i)) {
                    val m1 = dateList[j]?.substring(3, 5)?.toInt()
                    val m2 = dateList[j + 1]?.substring(3, 5)?.toInt()
                    val d1 = dateList[j]?.substring(0, 2)?.toInt()
                    val d2 = dateList[j + 1]?.substring(0, 2)?.toInt()
                    val y1 = dateList[j]?.substring(6)?.toInt()
                    val y2 = dateList[j + 1]?.substring(6)?.toInt()

                    if (m1 != null && m2 != null && d1 != null && d2 != null && y1 != null && y2 != null) {
                        if (y1 > y2) {
                            val t = dateList[j]
                            dateList[j] = dateList[j + 1]
                            dateList[j + 1] = t
                        } else if (y1 == y2) {
                            if (m1 > m2) {
                                val t = dateList[j]
                                dateList[j] = dateList[j + 1]
                                dateList[j + 1] = t

                            } else if (m1 == m2) {
                                if (d1 > d2) {
                                    val t = dateList[j]
                                    dateList[j] = dateList[j + 1]
                                    dateList[j + 1] = t
                                }
                            }
                        }
                    }
                }
            }
        }
        return dateList
    }

    fun getUpcomingWeekDatesRange(currentWeek: Int): String {
        val sdf = SimpleDateFormat("MMMM dd", Locale.getDefault())

        val dateList = mutableListOf<String>()

        val dateRange: IntRange
        /*var dateRange = 0..6
        when (currentWeek) {
            0 -> dateRange = 0..6 //currentWeek= (currentWeek + currentWeek * 6)
            //(currentWeek + currentWeek * 6)..currentWeek + 6
            1 -> dateRange = 7..13 //currentWeek= (currentWeek + currentWeek * 6)
            //(currentWeek + currentWeek * 6)..currentWeek + 6
            2 -> dateRange = 14..20
        }*/
        val curWeek: Int = (currentWeek + currentWeek * 6)
        dateRange = curWeek..curWeek + 6
        val calendar = Calendar.getInstance()
//        calendar.time = Date()
        calendar.add(
            Calendar.DAY_OF_WEEK,
            -(calendar.get(Calendar.DAY_OF_WEEK) - 1) + dateRange.first
        );
//        calendar.add(Calendar.DAY_OF_YEAR, dateRange.first)
        Log.d("dateRange", "day gap: ${dateRange.first}")
//        calendar[Calendar.DAY_OF_WEEK] = calendar.get(Calendar.DAY_OF_WEEK)
        for (i in dateRange) {
            dateList.add(sdf.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }

        return dateList[0].plus(" - ").plus(dateList[dateList.size - 1])
    }

    fun convertedServerDate() {
        val sdf = SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.SSSz", Locale.getDefault())
    }

    fun getConvertedDate(sourceDate: String?, timezone: String?): String? {

        var formattedDate: String? = null
        val originalFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())
//        originalFormat.timeZone = TimeZone.getTimeZone("GMT")

        val targetFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_WITH_AM_PM, Locale.getDefault())
//        timezone?.let{
//            t
//        }
        val date: Date?
        try {
            date = originalFormat.parse(sourceDate ?: "")
            if (date != null) formattedDate = targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }
    fun getConvertedDateInTime(sourceDate: String?): String? {

        var formattedDate: String? = null
        val originalFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())
//        originalFormat.timeZone = TimeZone.getTimeZone("GMT")

        val targetFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_IN_TIME, Locale.getDefault())
        val date: Date?
        try {
            date = originalFormat.parse(sourceDate ?: "")
            if (date != null) formattedDate = targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }


    fun getConvertedDateInDay(sourceDate: String?): String? {

        var formattedDate: String? = null
        val originalFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())
//        originalFormat.timeZone = TimeZone.getTimeZone("GMT")

        val targetFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_IN_DAY, Locale.getDefault())
        val date: Date?
        try {
            date = originalFormat.parse(sourceDate ?: "")
            if (date != null) formattedDate = targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }



    fun getConvertedLongDate(sourceDate: String?, timezone: String?): String? {

        var formattedDate: String? = null
//        originalFormat.timeZone = TimeZone.getTimeZone("GMT")

        val targetFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_WITH_AM_PM)
        timezone?.let {
            targetFormat.timeZone = TimeZone.getTimeZone(timezone)
        }
        val date: Date?
        try {
            date = Date(sourceDate?.toLong() ?: 0)
            formattedDate = targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun getConvertedLongDateInTime(sourceDate: String?): String? {

        var formattedDate: String? = null
//        originalFormat.timeZone = TimeZone.getTimeZone("GMT")

        val targetFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_IN_TIME, Locale.getDefault())
        val date: Date?
        try {
            date = Date(sourceDate?.toLong() ?: 0)
            formattedDate = targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }


    fun getSimpleDateFromLong(milliSeconds: Long, dateFormat: String?, timeZone: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        if(!timeZone.isNullOrBlank()){
            calendar.timeZone = TimeZone.getTimeZone(timeZone)
        }
        return formatter.format(calendar.time)
    }

    fun getConvertedLongDateInDate(sourceDate: String?): String? {

        var formattedDate: String? = null
//        originalFormat.timeZone = TimeZone.getTimeZone("GMT")

        val targetFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_IN_DAY, Locale.getDefault())
        val date: Date?
        try {
            date = Date(sourceDate?.toLong() ?: 0)
            formattedDate = targetFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun appendTimeWithDate(date: Long?, time: String?): String? {
        var appendedDate: String? = ""
        val dateFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_WITH_YEAR, Locale.getDefault())
        try {
            if (date != null) {
                val convertedDate: String? = dateFormat.format(date)
                appendedDate = convertedDate.plus(" ").plus(time?.replace("\n", " ")?.lowercase())
                Log.d("CalDate", "date+time: $appendedDate")
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return appendedDate
    }

    fun isSameEventCalDate(stDate1: String?, stDate2: String?): Boolean {
        var isSameDate = false
        val sdf: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_WITH_AM_PM, Locale.getDefault())
        try {
            val date1 = sdf.parse(stDate1 ?: "")
            val date2 = sdf.parse(stDate2 ?: "")
            if (date1 != null)
                isSameDate = date1.equals(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return isSameDate
    }


    fun isLaterTime(stDate1: String?, stDate2: String?): Boolean {
        var isSameDate = false
        val sdf: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.NORMAL_DATE_FORMAT, Locale.getDefault())
        try {
            val date1 = sdf.parse(stDate1 ?: "")
            val date2 = sdf.parse(stDate2 ?: "")
            if (date1 != null)
                isSameDate = date2.after(date1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return isSameDate
    }

    fun isWithinDateRange(
        stDate: String?,
        stDateMinTime: String?,
        stDateMaxTime: String?
    ): Boolean {
        val sdf: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_WITH_AM_PM, Locale.getDefault())
        try {
            val date = sdf.parse(stDate ?: "")
            val dateMinTime = sdf.parse(stDateMinTime ?: "")
            val dateMaxTime = sdf.parse(stDateMaxTime ?: "")

            if (date != null) {
                return !(date.before(dateMinTime) || date.after(dateMaxTime))
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return false
    }

    fun isWithinDateRange2(
        date: Long?,
        stDateMinTime: Long?,
        stDateMaxTime: Long?
    ): Boolean {
            if (date != null && stDateMaxTime!= null && stDateMinTime !=null) {
                return !((date < stDateMinTime!!) || (date > stDateMaxTime!!))
            }

        return false
    }

    private fun toCalendar(date: Date?): Calendar? {
        val cal = Calendar.getInstance()
        cal.time = date
        return cal
    }


    fun calculateLongDate(startDate: String?): String {
        return DateFormat.getDateInstance(DateFormat.LONG).format(startDate).toString()
    }

    fun notificationDateFormat(stDate: String?): String? {
        var formattedDate: String? = ""
        val apiDateFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())
        val dateFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.NORMAL_DATE_FORMAT2, Locale.getDefault())
        val timeFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.TIME_FORMAT, Locale.getDefault())
        try {
            val date = apiDateFormat.parse(stDate ?: "")
            if (date != null) {
                val convertedDate: String? = dateFormat.format(date)
                val convertedTime: String? = timeFormat.format(date)
                formattedDate = convertedDate.plus(" at ").plus(convertedTime).lowercase()
                Log.d("CalDate", "date+time: $formattedDate")
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    //dd/mm/yyyy
    fun getNormalDateFromApiDate(d: String?): String? {
        val inputFormat: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.API_DATE_FORMAT, Locale.getDefault())

        val outputDate: DateFormat =
            SimpleDateFormat(AppConstant.DATE_PATTERN.DATE_FORMAT_SIMPLE, Locale.getDefault())


        var inputText = d
        if (inputText != null) {
            val date: Date = inputFormat.parse(inputText) ?: Date()
            inputText = outputDate.format(date)
        }
        return inputText
    }

}