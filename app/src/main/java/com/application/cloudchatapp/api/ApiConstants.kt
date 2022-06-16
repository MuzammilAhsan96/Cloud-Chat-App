package com.application.cloudchatapp.api

object ApiConstants {
    const val ENQUIRY_YES = 1
    const val ENQUIRY_NO = 2
    const val OFFER = 0
    const val PRODUCT = 1
    const val BRAND = 2

    const val EMAIL_EXIST = "Email already exist"
    const val EMAIL_NOT_EXIST = "Email doesn't exist"

    interface USER_TYPE {
        companion object {
            const val ATTENDEE = 0
            const val ADMIN = 1
            const val BRAND = 2
            const val SPEAKER = 3
            const val STAFF = 4
            const val SUB_ADMIN = 5
        }
    }

    interface CONNECTIONS {
        companion object {
            const val ACCEPT = 1
            const val REJECT = 3
            const val CANCEL = 3
            const val BLOCK = 1
            const val UNBLOCK = 0
            const val CONNECTED = 4
            const val ENQUIRY_YES = 1
            const val ENQUIRY_NO = 2
            const val OFFER = 0
            const val PRODUCT = 1
            const val BRAND = 2
        }
    }

    interface QUESTIONNAIRE {
        companion object {
            //******* STATIC QUESTION TYPE *******//
            const val SECTOR = 1
            const val PLACE_OF_WORK = 2
            const val ROLE = 3
            const val COUNTRY = 4
            const val CITY = 5
            const val ADDRESS = 6
            const val NATIONALITY = 7

            //****** QUESTION TYPE *******//
            const val PLANE = 1
            const val MULTIPLE_CHOICE = 2
            const val SINGLE_SELECTION = 3
            const val MULTIPLE_TEXT = 6
        }
    }

    interface CONNECTION_TYPE {
        companion object {
            const val NOT_CONNECTED = 4
        }
    }

    interface CONNECTION_STATUS {
        companion object {
            const val MY_CONNECTION = 1
        }
    }

    interface MEETING {
        companion object {
            const val EMAIL = 0
            const val NOTIFICATION = 1
            const val MINUTE = 0
            const val HOUR = 1
            const val DAY = 2
        }
    }

    interface EVENT {
        companion object {
            const val CONFERENCE = 0
            const val MEETING = 1




            const val RESCHEDULE = 1
            const val PENDING = 0


            const val ACCEPT = 1
            const val REJECT = 2
            const val CANCEL = 3

        }
    }
}

