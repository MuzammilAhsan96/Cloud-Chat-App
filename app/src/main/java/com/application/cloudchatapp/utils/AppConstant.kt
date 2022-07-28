package com.application.cloudchatapp.utils

import com.application.cloudchatapp.model.base.LoginResponseModel
import com.application.cloudchatapp.model.base.UserDetailResponse


object AppConstant {

    const val CHAT_TABLE = "usersChat"
    const val USER_TABLE = "users"
    const val CHAT = "chat"
    const val SPLASH_DELAY: Long = 2000
    const val IMAGE_PICK_CODE = 1000

    //    const val REQUEST_CODE = 200
    const val PERMISSION_CODE = 1001
    const val NAME_MIN_LENGTH = 3
    //var USER_PROFILE: UserProfileData = UserProfileData()
    var USER_PROFILE: UserDetailResponse = UserDetailResponse()


    interface APP {
        companion object {

            const val EXHIBITION_NAME = "exhibition_name"
            const val ACCESS_TOKEN = "access_token"
            const val DEVICE_TOKEN = "device_token"

            //            const val FCM_TOKEN = "fcm_token"
            const val DEVICE_ID = "device_id"
            const val EXHIBITION_ID = "exhibition_id"
            const val EXHIBITION_IMAGE = "exhibition_image"

            //            const val DEVICE_TYPE = "android"
            const val DEVICE_TYPE = "2"
            const val USER_LOGGED_IN = "user_logged_in"
            const val IS_SWITCH = "is_switch"
            const val IS_QUESTIONNAIRE = "is_questionnaire"
        }
    }

    interface BK {
        companion object {
            const val CHAT_MODEL = "CHAT_MODEL"
            const val SENDER_ID = "SENDER_ID"
            const val FROM = "FROM"
            const val NOTIFICATION = "NOTIFICATION"
            const val NOTIFICATION_TYPE = "NOTIFICATION_TYPE"
            const val RESCHEDULE_DATA = "RESCHEDULE_DATA"
            const val ID = "ID"
            const val EXH_DATA = "EXH_DATA"
            const val EMAIL = "EMAIL"
            const val USER_DATA = "USER_DATA"
            const val EXH_ID = "EXH_ID"
            const val IS_CONFERENCE = "IS_CONFERENCE"
            const val SECTOR_ID = "SECTOR_ID"
            const val ROLE_ID = "ROLE_ID"
            const val PLACE_ID = "PLACE_ID"
            const val QUESTION_DATA = "QUESTION_DATA"
            const val RELATED_QUESTION_DATA = "RELATED_QUESTION_DATA"
            const val QUESTION_DATA_INDEX = "QUESTION_DATA_INDEX"
            const val USER_ANS = "USER_ANS"
            const val USER_ANS_LIST = "USER_ANS_LIST"
            const val IS_DONE_EVENT = "IS_DONE_EVENT"
            const val BRAND_DATA = "BRAND_DATA"
            const val BRAND_DETAIL = "BRAND_DETAIL"
            const val BRAND_ID = "BRAND_ID"
            const val ADDED_ADDRESS = "ADDED_ADDRESS"
            const val SPEAKER_DATA = "SPEAKER_DATA"
            const val PRODUCT_ID = "PRODUCT_ID"
            const val OFFER_ID = "OFFER_ID"
            const val ALL = "ALL"
            const val MOST_VISITED = "MOST_VISITED"
            const val FEATURED_PRODUCTS = "FEATURED_PRODUCTS"
            const val EVENT_DATA = "EVENT_DATA"
            const val UPCOMING_EVENTS = "Upcoming Events"
            const val PAST_EVENTS = "Past Events"
            const val SCAN_ID = "SCAN_ID"
            const val PROFILE_DATA = "PROFILE_DATA"
            const val PRODUCT_DATA = "PRODUCT_DATA"
            const val OFFER_DATA = "OFFER_DATA"
            const val NAME = "NAME"
            const val TICKET_ID = "TICKET_ID"
            const val USER_TICKET = "USER_TICKET"
            const val T_RESULT = "T_RESULT"
            const val VIDEO_URL = "VIDEO_URL"
            const val VIDEO_TIME = "VIDEO_TIME"
            const val IS_PLAYING = "IS_PLAYING"
        }
    }

    interface NOTIFICATION_TYPE {
        companion object {

            const val REQUEST = 1
            const val ACCEPT = 3
            const val REJECT = 4
            const val CANCEL = 5


            const val C0NN_SCAN = 13
            const val EVENT_REM = 8

            const val RESCHEDULE = 10

            const val TICKET = 12


        }
    }

    interface DOWNLOAD_ACTION {
        companion object {
            const val PROGRESS = "com.app.pbw.progress"
            const val START = "com.app.pbw.start"
            const val REMOVE = "com.app.pbw.remove"
            const val PAUSE = "com.app.pbw.pause"
            const val FAILED = "com.app.pbw.failed"
            const val COMPLETED = "com.app.pbw.completed"
        }
    }

    interface REQUEST_CODE {
        companion object {
            val APP_UPDATE_REQUEST: Int = 2321
            const val APP_SETTING = 1109
            const val CAMERA_REQUEST = 200
            const val CONNECTION_REQUEST = 100
            const val WISHLIST_REQUEST = 300
            const val PRODUCT_WISHLIST_REQUEST = 301
            const val OFFER_WISHLIST_REQUEST = 302
            const val BRAND_WISHLIST_REQUEST = 303
            const val PROFILE_REQUEST = 500
            const val RESULT_REQUEST = 400
            const val VIDEO_REQUEST = 1
        }
    }

    interface LOCATION {
        companion object {
            const val ADDRESS = "ADDRESS"
            const val CITY = "CITY"
            const val STATE = "STATE"
            const val COUNTRY = "COUNTRY"
            const val POSTAL_CODE = "POSTAL_CODE"
            const val KNOWN_NAME = "KNOWN_NAME"
        }
    }

    interface MEETING_CODE {
        companion object {
            const val PENDING = 0
            const val ACCEPT = 1
            const val REJECT = 2
            const val CANCEL = 3
            const val EVENT_TYPE_MEETING = "1"
            const val EVENT_TYPE_CONFERENCE = "0"
            const val TIME_START = 0
            const val TIME_END = 1
        }
    }

    interface WISHLIST_XLS_TYPE {
        companion object {
            const val PRODUCT = "1"
            const val BRAND = "2"
            const val OFFER = "3"
        }
    }


    interface QUESTION_TYPE {
        companion object {
            const val SECTOR = 1
            const val PLACE_OF_WORK = 2
            const val ROLE = 3
            const val COUNTRY = 4
            const val NATIONALITY = 7
        }
    }

    interface DATE_PATTERN {
        companion object {
            const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy hh:mm:ss a"
            const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX"
            const val API_DATE_FORMAT_RECEIVED = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
            const val API_DATE_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ssXXX"
            const val NORMAL_DATE_FORMAT = "dd/MM/yyyy hh:mma"
            const val RESCHEDULE_DATE_FORMAT = "dd-MM-yyyy-HH-mm-ss"
            const val NORMAL_DATE_FORMAT2 = "dd/MM/yyyy"
            const val DATE_FORMAT_WITH_AM_PM = "dd MM yyyy hh:mm aa"
            const val DATE_FORMAT_IN_WORDS = "EEE, d MMM yyyy hh:mm aa"
            const val DATE_FORMAT_IN_TIME = "EEE, hh:mm aa"
            const val DATE_FORMAT_IN_DAY = "dd MMMM yyyy"
            const val DATE_FORMAT_WITH_YEAR = "dd MM yyyy"
            const val DATE_FORMAT_SIMPLE = "dd/MM/yyyy"
            const val TIME_FORMAT = "hh:mm aa"
        }
    }

    interface SCAN_CODE {
        companion object {
            const val BRAND = 1
            const val OFFER = 2
            const val PRODUCT = 3
            const val CONNECTION = 4
        }
    }

    interface CONNECTION_STATUS {
        companion object {
            const val NOT_CONNECT = "NOT_CONNECT"
            const val MY_CONNECTION = "MY_CONNECTION"
            const val REQUEST_RECEIVED = "REQUEST_RECEIVED"
            const val REQUEST_SENT = "REQUEST_SENT"
            const val BLOCKED = "BLOCKED"
        }
    }

    val timeList = listOf(
        "12:00\nAM",
        "01:00\nAM",
        "02:00\nAM",
        "03:00\nAM",
        "04:00\nAM",
        "05:00\nAM",
        "06:00\nAM",
        "07:00\nAM",
        "08:00\nAM",
        "09:00\nAM",
        "10:00\nAM",
        "11:00\nAM",
        "12:00\nPM",
        "01:00\nPM",
        "02:00\nPM",
        "03:00\nPM",
        "04:00\nPM",
        "05:00\nPM",
        "06:00\nPM",
        "07:00\nPM",
        "08:00\nPM",
        "09:00\nPM",
        "10:00\nPM",
        "11:00\nPM"
    )


    interface TICKET_TYPE {
        companion object {
            const val DAY = 1
            const val COMBO = 2
            const val CONFERENCE = 3

            const val SESSION = 4
            const val SESSION_MIS = 5


            const val ONLINE = 1
            const val OFFLINE = 2
            const val HYBRID = 3
        }
    }

    interface USER_TYPE {
        companion object {
            const val ATTENDEE = 0
            const val ADMIN = 1
            const val BRAND = 2
            const val SPEAKER = 3
            const val STAFF = 4
            const val SUBADMIN = 5
        }
    }

    interface MEDIA_TYPE {
        companion object {
            const val VIDEO = "V"
            const val IMAGE = "I"
            const val SEEK_TIME = "SEEK_TIME"
        }
    }

}