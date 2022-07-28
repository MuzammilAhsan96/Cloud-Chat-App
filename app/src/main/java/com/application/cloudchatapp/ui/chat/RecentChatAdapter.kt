package com.application.cloudchatapp.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.pbgevents.utils.DateUtils
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.callback.CallbackType
import com.application.cloudchatapp.callback.RootCallback
import com.application.cloudchatapp.databinding.AdapterRecentChatBinding
import com.application.cloudchatapp.extension.visible
import com.application.cloudchatapp.model.base.ChatRecentUserModel
import com.application.cloudchatapp.model.base.ChatUserModel
import com.application.cloudchatapp.utils.AppUtil
import com.application.cloudchatapp.utils.PreferenceKeeper
import com.bumptech.glide.Glide

class RecentChatAdapter(
    var activity: BaseActivity
) :
    RecyclerView.Adapter<RecentChatAdapter.MyViewHolder>() {

    private var dataList: MutableList<ChatRecentUserModel?>? = null
    private var userList: MutableList<ChatUserModel?>? = null
    var user: ChatUserModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            AdapterRecentChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList?.get(position)
        holder.bind(data, position)
    }

    override fun getItemCount(): Int = 5//dataList?.size ?: 0

    fun setData(
        dataList: MutableList<ChatRecentUserModel?>?,
        userList: MutableList<ChatUserModel?>?
    ) {
        this.dataList = dataList
        this.userList = userList
        notifyDataSetChanged()
    }

    private var rootCallback: RootCallback<ChatRecentUserModel>? = null
    fun setRootCallback(rootCallback: RootCallback<ChatRecentUserModel>?) {
        this.rootCallback = rootCallback
    }

    inner class MyViewHolder(var binding: AdapterRecentChatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ChatRecentUserModel?, position: Int) {
            //val data = dataList?.get(position)
            /*binding.tvMessage.text = data?.msgBody
            binding.tvMessage.visible()
            binding.tvTime.text =
                DateUtils.getFormattedDate(activity, DateUtils.milliseconds(data?.time))*/
            /*if (PreferenceKeeper.getString(PrefManager.KEY_ID) == data?.senderDetail?.id) {
                *//*if (userList?.contains(data?.receiverDetail) == true) {
                    user = userList?.indexOf(data?.receiverDetail)
                        ?.let { userList?.get(it) }
                }*//*
                for (j in 0..userList?.size?.minus(1)!!) {
                    if (userList?.get(j)?.id == data?.receiverDetail?.id) {
                        user = userList?.get(j)
                    }
                }
                binding.tvName.text =
                    user?.firstName.plus(" ").plus(user?.lastName)
                Glide.with(activity).load(user?.profileImg)
                    .into(binding.ivDp)
            } else {
                for (j in 0..userList?.size?.minus(1)!!) {
                    if (userList?.get(j)?.id == data?.senderDetail?.id) {
                        user = userList?.get(j)
                    }
                }
                binding.tvName.text =
                    user?.firstName.plus(" ").plus(user?.lastName)
                Glide.with(activity).load(user?.profileImg)
                    .into(binding.ivDp)
            }*/

            binding.clMain.setOnClickListener {
                AppUtil.preventTwoClick(it)
                rootCallback?.onRootCallback(
                    position,
                    data,
                    CallbackType.RECENT_CHAT,
                    it
                )
            }
        }
    }

}
