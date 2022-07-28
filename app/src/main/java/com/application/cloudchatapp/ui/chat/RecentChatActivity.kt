package com.application.cloudchatapp.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.R
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.callback.CallbackType
import com.application.cloudchatapp.callback.RootCallback
import com.application.cloudchatapp.databinding.ActivityRecentChatBinding
import com.application.cloudchatapp.extension.gone
import com.application.cloudchatapp.extension.visible
import com.application.cloudchatapp.model.base.ChatRecentUserModel
import com.application.cloudchatapp.model.base.ChatUserModel
import com.application.cloudchatapp.model.base.UserDetailResponse
import com.application.cloudchatapp.ui.auth.LoginActivity
import com.application.cloudchatapp.utils.AppConstant
import com.application.cloudchatapp.utils.AppUtil
import com.application.cloudchatapp.utils.PreferenceKeeper
import com.application.cloudchatapp.utils.SortByDateChatConversation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RecentChatActivity : BaseActivity(), RootCallback<Any> {
    private lateinit var ui: ActivityRecentChatBinding
    var recentchatadapter: RecentChatAdapter? = null
    private val firestoreDB = Firebase.firestore
    private var recentChatList: MutableList<ChatRecentUserModel?>? = mutableListOf()
    private var recentUsersList: MutableList<ChatUserModel?>? = mutableListOf()
    private var usersIds: MutableList<String?>? = mutableListOf()
    private val TAG = "RecentChatActivity"
    private var search = ""
    var user: ChatUserModel? = null
    private val allUsers = mutableListOf(
        UserDetailResponse(
            id = "111111",
            email = "muzzu@gmail.com",
            firstName = "Muzzu",
            lastName = "Ahsan",
            password = "12345678"
        ), UserDetailResponse(
            id = "222222",
            email = "pushkar@gmail.com",
            firstName = "Pushkar",
            lastName = "Dubey",
            password = "12345678"
        ),
        UserDetailResponse(
            id = "333333",
            email = "vipul@gmail.com",
            firstName = "Vipul",
            lastName = "Khankriyal",
            password = "12345678"
        ), UserDetailResponse(
            id = "444444",
            email = "mukku@gmail.com",
            firstName = "Mukku",
            lastName = "Ahsan",
            password = "12345678"
        ),
        UserDetailResponse(
            id = "555555",
            email = "raju@gmail.com",
            firstName = "Raju",
            lastName = "Pandey",
            password = "12345678"
        )
    )

    override fun layoutRes(): ViewBinding {
        return ActivityRecentChatBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = binding as ActivityRecentChatBinding
        //getIntentData()
        println("TTTTTTTTTTTTTTTTTttt " + PreferenceKeeper.getUser())
        setupUI()
        setupAdapter()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        //search = binding.etMainSearchBox.text.toString().trim().lowercase()
        getRecentUsers()
    }

    private fun setupUI() {

        //Setting header
        ui.includeHeader.tvTitle.text = getString(R.string.txt_recentchat)
    }

    private fun setListeners() {

        //Setting header
        ui.includeHeader.ivBack.setOnClickListener {
            AppUtil.preventTwoClick(it)
            onBackPressed()
        }
        ui.tvLogout.setOnClickListener {
            AppUtil.preventTwoClick(it)
            PreferenceKeeper.clearAllPrefrance()
            launchActivity(LoginActivity::class.java)
            finish()
        }
    }


    private fun setupAdapter() {
        /*recentChatList?.let { list ->
            Collections.sort(
                list,
                SortByDateChatConversation.SortByDateRecentUser()
            )
        }*/
        recentchatadapter = RecentChatAdapter(this)
        ui.recyclerRecentlist.setHasFixedSize(true)
        ui.recyclerRecentlist.adapter = recentchatadapter
        recentchatadapter?.setRootCallback(this as RootCallback<ChatRecentUserModel>)
    }

    private fun getRecentUsers() {
        showProgressBar()
        //recentUsersList?.clear()
        val docRef = firestoreDB.collection(AppConstant.USER_TABLE)
        docRef.addSnapshotListener { documentSnapshot, e ->
            when {
                e != null -> e.message?.let { Log.e("ERROR", it) }
                documentSnapshot != null -> {
                    recentUsersList?.clear()
                    for (document in documentSnapshot) {
                        val docRef =
                            firestoreDB.collection(AppConstant.USER_TABLE).document(document.id)
                        docRef.get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val document = task.result
                                if (document != null) {
                                    Log.d(TAG, "${document.data}")
                                    if (recentUsersList.isNullOrEmpty())
                                        getRecentChat()
                                    val user: ChatUserModel? =
                                        task.result.toObject(ChatUserModel::class.java)
                                    if (user != null) {
                                        recentUsersList?.add(user)
                                    }
                                    //println("DDDDDDDDDDDDDDDDDDDD if " + recentUsersList)
                                    println("DDDDDDDDDDDDDDDDDDDD if " + user)
                                } else {
                                    Log.d(TAG, "No such document")
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.exception)
                            }
                        }
                    }
                }
            }
        }
        hideProgressBar()//temporary
    }

    private fun getRecentChat() {
        recentChatList?.clear()
        val docRef = firestoreDB.collection(AppConstant.CHAT_TABLE)
        docRef.addSnapshotListener { documentSnapshot, e ->
            when {
                e != null -> e.message?.let { Log.e("ERROR", it) }
                documentSnapshot != null -> {
                    recentChatList?.clear()
                    for (document in documentSnapshot) {
                        if (document.id.contains(PreferenceKeeper.getString(PreferenceKeeper.KEY_ID)!!)) {
                            val docRef =
                                firestoreDB.collection(AppConstant.CHAT_TABLE).document(document.id)
                            docRef.get().addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val document = task.result
                                    if (document != null) {
                                        Log.d(TAG, "${document.data}")
                                        val user: ChatRecentUserModel? =
                                            task.result.toObject(ChatRecentUserModel::class.java)
                                        if (user != null && recentChatList?.contains(user) == false) {
                                            recentChatList?.add(user)
                                        }
                                        val df: DateFormat =
                                            SimpleDateFormat(AppConstant.DATE_PATTERN.SIMPLE_DATE_FORMAT)
                                        val currentdate: String =
                                            df.format(Calendar.getInstance().time)
                                        /* recentChatList.forEach {
                                            timedifferencelist.add(diffrentBetweenTwoTime(it.time.toString(),currentdate))
                                        }*/
                                        //setupAdapter(recentChatList)
                                        recentChatList?.let { list ->
                                            Collections.sort(
                                                list,
                                                SortByDateChatConversation.SortByDateRecentUser()
                                            )
                                        }
                                        println("RRRRRRRRRRRRRRRRRRRRRr " + recentChatList)
                                        if (search.isNullOrEmpty()) {
                                            if (recentChatList?.isEmpty() == true) {
                                                //ui.clNoData.visible()
                                                ui.recyclerRecentlist.gone()
                                            } else {
                                                recentchatadapter?.setData(
                                                    recentChatList,
                                                    recentUsersList
                                                )
                                                for (i in 0..recentUsersList?.size?.minus(1)!!) {
                                                    if (usersIds?.contains(recentUsersList?.get(i)?.id) == false)
                                                        usersIds?.add(recentUsersList?.get(i)?.id)
                                                }
                                                println("DDDDDDDDDDDDDDDDDDDD " + recentUsersList)
                                                println("DDDDDDDDDDDDDDDDDDDD " + usersIds)
                                                //ui.clNoData.gone()
                                                ui.recyclerRecentlist.visible()
                                            }
                                        } else {
                                            searchUser()
                                        }
                                    } else {
                                        Log.d(TAG, "No such document")
                                    }
                                } else {
                                    Log.d(TAG, "get failed with ", task.exception)
                                }
                            }
                        }
                    }
                    hideProgressBar()
                }
            }
        }
    }

    private fun searchUser() {
        val filtermodelist = filter(recentChatList, search)
        //setupAdapter(filtermodelist as MutableList<ChatRecentUserModel?>?)
        filtermodelist.let { list ->
            Collections.sort(
                list,
                SortByDateChatConversation.SortByDateRecentUser()
            )
        }
        if (filtermodelist.isEmpty()) {
            //ui.clNoData.visible()
            ui.recyclerRecentlist.gone()
        } else {
            recentchatadapter?.setData(
                filtermodelist as MutableList<ChatRecentUserModel?>?,
                recentUsersList
            )
            //ui.clNoData.gone()
            ui.recyclerRecentlist.visible()
        }
    }

    fun filter(
        contactList: MutableList<ChatRecentUserModel?>?,
        query: String?
    ): MutableList<ChatRecentUserModel> {
        var recieverName = ""
        //var senderName= ""
        val filteredModeList = mutableListOf<ChatRecentUserModel>()
        if (contactList != null) {
            for (model in contactList) {
                if (PreferenceKeeper.getString(PreferenceKeeper.KEY_ID) == model?.senderDetail?.id) {
                    recieverName = model?.receiverDetail?.firstName!!.lowercase()
                } else {
                    recieverName = model?.senderDetail?.firstName!!.lowercase()
                }
                if ((query?.let { recieverName.contains(it) } == true)) {
                    filteredModeList.add(model)
                }
            }
        }
        return filteredModeList
    }

    override fun onRootCallback(index: Int, data: Any?, type: CallbackType, view: View?) {
        super.onRootCallback(index, data, type, view)
        when (type) {
            CallbackType.RECENT_CHAT -> {
                recentUsersList
                val bundle = Bundle()
                if (data is ChatRecentUserModel) {
                    if (PreferenceKeeper.getString(PreferenceKeeper.KEY_ID) == data.senderDetail?.id) {
                        for (j in 0..recentUsersList?.size?.minus(1)!!) {
                            if (recentUsersList?.get(j)?.id == data.receiverDetail?.id) {
                                user = recentUsersList?.get(j)
                            }
                        }
                        bundle.putSerializable(
                            AppConstant.BK.CHAT_MODEL,
                            user as Serializable
                        )
                    } else {
                        for (j in 0..recentUsersList?.size?.minus(1)!!) {
                            if (recentUsersList?.get(j)?.id == data.senderDetail?.id) {
                                user = recentUsersList?.get(j)
                            }
                        }
                        bundle.putSerializable(
                            AppConstant.BK.CHAT_MODEL,
                            user as Serializable
                        )
                    }
                }
                launchActivity(ChatActivity::class.java, bundle)
            }
        }
    }

}