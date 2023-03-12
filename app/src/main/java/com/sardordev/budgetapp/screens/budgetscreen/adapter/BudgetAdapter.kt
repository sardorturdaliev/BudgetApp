package com.sardordev.budgetapp.screens.budgetscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sardordev.budgetapp.data.model.BudgetEntity
import com.sardordev.budgetapp.databinding.ItemCardBinding

class BudgetAdapter(val listener: ClickListenerItem) :
    ListAdapter<BudgetEntity, BudgetAdapter.Holder>(diff) {

    inner class Holder(val view: ItemCardBinding) : RecyclerView.ViewHolder(view.root) {

        fun onbind(budgetEntity: BudgetEntity) {
            view.tvbudgetname.text = budgetEntity.categoryName
            view.tvBudgetSum.text = budgetEntity.amountBudgets.toString()

            itemView.setOnClickListener {
                listener.clickItem(budgetEntity)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onbind(getItem(position))
    }

    object diff : DiffUtil.ItemCallback<BudgetEntity>() {
        override fun areItemsTheSame(oldItem: BudgetEntity, newItem: BudgetEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BudgetEntity, newItem: BudgetEntity): Boolean {
            return oldItem == newItem
        }
    }


    interface ClickListenerItem {

        fun clickItem(budgetEntity: BudgetEntity)


    }


}