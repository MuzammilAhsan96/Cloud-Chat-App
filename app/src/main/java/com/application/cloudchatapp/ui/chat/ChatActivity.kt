package com.application.cloudchatapp.ui.chat

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.R
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.callback.RootCallback
import com.application.cloudchatapp.databinding.ActivityChatBinding
import com.application.cloudchatapp.extension.gone
import com.application.cloudchatapp.extension.showToast
import com.application.cloudchatapp.extension.visible
import com.application.cloudchatapp.model.base.ChatConversationModel
import com.application.cloudchatapp.model.base.ChatRecentUserModel
import com.application.cloudchatapp.model.base.ChatUserModel
import com.application.cloudchatapp.model.base.UserDetailResponse
import com.application.cloudchatapp.utils.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class ChatActivity : BaseActivity(), RootCallback<Any> {
    private lateinit var ui: ActivityChatBinding
    private var chatAdapter: ChatAdapter? = null
    private val firestoreDB = Firebase.firestore
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
    private var recentChatList: MutableList<ChatConversationModel?>? = ArrayList()

    //private var chatViewModel: ChatViewModel? = null
    private var roomId1: String = "123"
    private var roomId2: String = "321"
    private var finalRoom: String = ""
    private var senderDetail: ChatUserModel? = null
    private var receiverDetail: ChatUserModel? = null

    override fun layoutRes(): ViewBinding {
        return ActivityChatBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = binding as ActivityChatBinding
        getIntentData()
        setViewModel()
        //setObserver()
        setListeners()
        setAdapter()
        setupUI()
        //setBroadcastReceiver()
    }

    private fun getIntentData() {
        val bundle = intent.extras
        receiverDetail =
            bundle?.getSerializable(AppConstant.BK.CHAT_MODEL) as ChatUserModel?
        //callingScreen = bundle?.getString(AppConstant.BK.CALLING_SCREEN)?.toInt()
    }

    private fun setViewModel() {
        /*val chaRepository = ChatRepository()
        chatViewModel = ViewModelProvider(
            this,
            ChatVMFactory(chaRepository)
        ).get(ChatViewModel::class.java)*/
    }

    fun setupUI() {

        val receiverId = receiverDetail?.id
        val receiverProfile = receiverDetail?.profileImg
        val receiverName = receiverDetail?.firstName.plus(" ").plus(receiverDetail?.lastName)
        val senderUser = PreferenceKeeper.getUser()
        val senderId = senderUser?.id

        ui?.txtUsername?.text = receiverName
        GlideUtils.loadSmallImage(ui?.ivRecommendedDoctorDp, receiverProfile)
        val currentTime: String = simpleDateFormat.format(Date())
        senderDetail = ChatUserModel(
            id = senderId,
            firstName = senderUser?.firstName,
            lastName = senderUser?.lastName,
            profileImg = senderUser?.profile_pic,
            time = currentTime
        )
        ui?.tvNoData?.text =
            getString(R.string.no_say_hey).plus(" ").plus(receiverDetail?.firstName)

        roomId1 = "${senderId}_${receiverId}"
        roomId2 = "${receiverId}_$senderId"

        Log.i("SSSSSSS", " receiverId ${receiverId}")
        Log.i("SSSSSSS", "revieverName ${receiverName}")
        Log.i("SSSSSSS", "senderUser ${senderId}")
        Log.i("SSSSSSS", "Name ${senderDetail?.firstName}")

        getFinalRoom(roomId1)
    }

    private fun getFinalRoom(room: String) {
        //  PDialog.showProgressBar(this)
        Firebase.firestore
            .collection(AppConstant.CHAT_TABLE)
            .document(room)
            .collection(AppConstant.CHAT)
            .get()
            .addOnSuccessListener { documents ->

                val d = documents.size()
                if (d > 0) {
                    finalRoom = room
                } else {
                    if (roomId2 != room) {
                        getFinalRoom(roomId2)
                    } else {
                        finalRoom = room
                    }
                }

                Log.i("KKKKKK", "finalRoom11  ${finalRoom}")

                //PDialog.hideProgressBar(this)

                if (!TextUtils.isEmpty(finalRoom)) {
                    getCallbackData()
                }
            }
            .addOnFailureListener { exception ->
                //PDialog.hideProgressBar(this)
                Log.w("KKKK", "Error getting documents:", exception)
            }
    }

    private fun getCallbackData() {
        Firebase.firestore
            .collection(AppConstant.CHAT_TABLE)
            .document(finalRoom)
            .collection(AppConstant.CHAT)
            .addSnapshotListener { documents, e ->

                Log.d("KKKK", "callbback $e")

                recentChatList = documents?.toObjects(ChatConversationModel::class.java)
                setDataToAdapter()
            }
    }

    private fun setDataToAdapter() {
        //recentChatList?.sortBy { it?.time }
        recentChatList?.let { list ->
            Collections.sort(
                list,
                SortByTimeChatConversation.SortByTimeUser()
            )
        }
        recentChatList?.reverse()
        if (recentChatList?.isEmpty() == true) {
            ui.clNoMessage.visible()
            ui.recyclerChat.gone()
        } else {
            chatAdapter?.setData(recentChatList)
            Log.i("KKKKK", "recentChatList   ${recentChatList?.size}")
            if (chatAdapter?.itemCount ?: 0 > 0) {
                ui.recyclerChat.smoothScrollToPosition(chatAdapter?.itemCount ?: 0 - 1)
            }
            ui.clNoMessage.gone()
            ui.recyclerChat.visible()
        }
    }


    private fun setAdapter() {
        chatAdapter = ChatAdapter(this)
        ui.recyclerChat.setHasFixedSize(true)
        ui.recyclerChat.adapter = chatAdapter
        chatAdapter?.setRootCallback(this as RootCallback<ChatConversationModel>)
    }


    private fun setListeners() {
        ui.ivBack.setOnClickListener {
            AppUtil.preventTwoClick(it)
            onBackPressed()
        }

        ui.btnSend.setOnClickListener {
            AppUtil.preventTwoClick(it)

            val msg = ui.chatEdittext.text.toString().trim()
            ui.chatEdittext.setText("")
            /*mediaType = 0
            selectedFileName = null*/
            if (msg.isEmpty()) {
                showToast(getString(R.string.pleaseentermessage))
            } else {
                val currentTime: String = simpleDateFormat.format(Date())
                val userChat = ChatConversationModel(
                    chat_id = finalRoom,
                    device_type = getString(R.string.deviceType),
                    sender_id = senderDetail?.id,
                    receiver_id = receiverDetail?.id,
                    msgBody = msg,
                    time = currentTime,
                    /* mediaName = null,
                     mediaType = mediaType,
                     mediaUrl = null,*/
                )

                val chatDocument =
                    firestoreDB
                        .collection(AppConstant.CHAT_TABLE)
                        .document(finalRoom)

                chatDocument.collection(AppConstant.CHAT)
                    .add(userChat)
                    .addOnSuccessListener {
                        Log.i("KKKKKK", "chatDocument ADD  ")
                        setRecentMsg(
                            chatDocument,
                            currentTime,
                            msg
                        )
                    }
                    .addOnFailureListener { e ->
                        Log.w("TAG", "Error adding document", e)
                    }
            }
        }


    }

    private fun setRecentMsg(
        chatDocument: DocumentReference?,
        currentTime: String?,
        msg: String?,
    ) {
        val userRecentChat = ChatRecentUserModel(
            chat_id = finalRoom,
            // device_type = getString(R.string.deviceType),
            sender_id = senderDetail?.id,
            receiver_id = receiverDetail?.id,
            msgBody = msg,
            time = currentTime,
            /* mediaName = selectedFileName,
             mediaType = mediaType,
             mediaUrl = mediaUrl,*/
            senderDetail = senderDetail,
            receiverDetail = receiverDetail
        )

        chatDocument?.set(userRecentChat)
            ?.addOnSuccessListener { documentReference ->
                //getChatConversation()
                Log.d("TAG", "DocumentSnapshot added with ID: $documentReference")
            }
            ?.addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

}