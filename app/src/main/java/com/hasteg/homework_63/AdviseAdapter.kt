package com.hasteg.homework_63

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hasteg.homework_63.databinding.ItemAdviseBinding

class AdviseAdapter(private val list: ArrayList<String>, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<AdviseAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemAdviseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    inner class MessageViewHolder(private val binding: ItemAdviseBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("Set")
        fun onBind(text: String) {
            binding.itemText.text = "#$text"
            itemView.setOnClickListener {
                clickListener(text)
            }

        }
    }

}