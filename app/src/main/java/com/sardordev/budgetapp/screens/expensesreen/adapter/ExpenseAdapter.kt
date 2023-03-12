package com.sardordev.budgetapp.screens.expensesreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sardordev.budgetapp.R
import com.sardordev.budgetapp.data.model.ExpenceEntity
import com.sardordev.budgetapp.databinding.ItemExpenceBinding

class ExpenseAdapter : ListAdapter<ExpenceEntity, ExpenseAdapter.Holder>(diff) {

    inner class Holder(val binding: ItemExpenceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(expenceEntity: ExpenceEntity) {
            binding.expenceName.text = expenceEntity.expenceName
            binding.expenceamount.text = expenceEntity.expenceAmount.toString()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        ItemExpenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.binding.root.context,
                R.anim.recycleranim
            )
        )

        holder.onbind(getItem(position))
    }


    object diff : DiffUtil.ItemCallback<ExpenceEntity>() {
        override fun areItemsTheSame(oldItem: ExpenceEntity, newItem: ExpenceEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExpenceEntity, newItem: ExpenceEntity): Boolean {
            return oldItem == newItem
        }

    }
}