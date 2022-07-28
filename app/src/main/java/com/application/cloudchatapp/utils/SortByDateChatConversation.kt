package com.application.cloudchatapp.utils

import com.application.cloudchatapp.model.base.ChatConversationModel
import com.application.cloudchatapp.model.base.ChatRecentUserModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by GAURAV KUMAR on 28,October,2021
 * Quytech,
 */
class SortByDateChatConversation : Comparator<ChatConversationModel?> {
    private val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.getDefault())
    // Method of this class
    // @Override
    /*override fun compare(a: ChatConversationModel, b: ChatConversationModel): Int {

        // Returning the value after comparing the objects
        // this will sort the data in Ascending order
        return a.time.compareTo(b.time)
    }*/

    override fun compare(a: ChatConversationModel?, b: ChatConversationModel?): Int {
        //if (a != null && b != null) {
        return b?.time?.let { simpleDateFormat.parse(a?.time).compareTo(simpleDateFormat.parse(it)) }!!
        //}
    }

    class SortByDateRecentUser : Comparator<ChatRecentUserModel?> {
        private val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.getDefault())
        // Method of this class
        // @Override
        /*override fun compare(a: ChatConversationModel, b: ChatConversationModel): Int {

            // Returning the value after comparing the objects
            // this will sort the data in Ascending order
            return a.time.compareTo(b.time)
        }*/

        override fun compare(b: ChatRecentUserModel?, a: ChatRecentUserModel?): Int {
            //if (a != null && b != null) {
            return b?.time?.let { simpleDateFormat.parse(a?.time).compareTo(simpleDateFormat.parse(it)) }!!
            //}
        }
    }
}

