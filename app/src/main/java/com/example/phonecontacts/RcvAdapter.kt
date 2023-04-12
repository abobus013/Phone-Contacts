package com.example.phonecontacts

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phonecontacts.databinding.ListContactsBinding
import kotlin.random.Random

class RcvAdapter(): RecyclerView.Adapter<RcvAdapter.ContactViewHolder>() {

    private var onClickUserListener: ((Contacts) -> Unit)? = null

    fun setOnUserClickListener(block: (Contacts) -> Unit) {
        onClickUserListener = block
    }

    inner class ContactViewHolder(private val binding: ListContactsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val position = models[adapterPosition]
            binding.tvName.text = position.name
            if (position.img != null) {
                binding.ivPerson.setImageBitmap(position.img)
            } else {
                val colorList = listOf("#AF5BF5", "#FE62B7", "#4DCDE5", "#FA8F3D", "#FBC834", "#5BB972")
                val randomNumber = Random.nextInt(0, 6)
                binding.linear.setBackgroundColor(Color.parseColor(colorList[randomNumber]))
                binding.ivPerson.setImageResource(R.drawable.ic_round_person_24)
            }
            binding.root.setOnClickListener {
                onClickUserListener?.invoke(position)
            }
        }
    }

    var models = mutableListOf<Contacts>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ListContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind()
    }
}
