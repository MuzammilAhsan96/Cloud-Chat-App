package com.application.cloudchatapp.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.pbgevents.utils.DateUtils
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.callback.RootCallback
import com.application.cloudchatapp.databinding.ItemChatReceiveBinding
import com.application.cloudchatapp.databinding.ItemChatSendBinding
import com.application.cloudchatapp.extension.visible
import com.application.cloudchatapp.model.base.ChatConversationModel

class ChatAdapter(var activity: BaseActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList: MutableList<ChatConversationModel?>? = ArrayList()

    companion object {
        private const val SENDER = 0
        private const val RECEIVER = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            SENDER -> {
                MyViewHolderSender(
                    ItemChatSendBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            RECEIVER -> {
                MyViewHolderReceiver(
                    ItemChatReceiveBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList?.get(position)
        when (holder) {
            is MyViewHolderSender -> data?.let { holder.bind(it, position) }
            is MyViewHolderReceiver -> data?.let { holder.bind(it, position) }
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    private var rootCallback: RootCallback<ChatConversationModel>? = null
    fun setRootCallback(rootCallback: RootCallback<ChatConversationModel>?) {
        this.rootCallback = rootCallback
    }

    fun setData(dataList: MutableList<ChatConversationModel?>?) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    inner class MyViewHolderSender(var binding: ItemChatSendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatConversationModel?, position: Int) {
            binding.tvTime.text =
                DateUtils.getFormattedDate(activity, DateUtils.milliseconds(data?.time))
            binding.tvMessage.text = data?.msgBody
            binding.tvMessage.visible()
        }

    }

    inner class MyViewHolderReceiver(var binding: ItemChatReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatConversationModel?, position: Int) {
            binding.tvTime.text =
                DateUtils.getFormattedDate(activity, DateUtils.milliseconds(data?.time))

            binding.tvMessage.text = data?.msgBody
            binding.tvMessage.visible()

        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = dataList?.get(position)
        return if (item?.sender_id == "PrefManager.getString(PrefManager.KEY_ID)")
            SENDER
        else {
            RECEIVER
        }
    }


}
