package com.sardordev.budgetapp.screens.expensesreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sardordev.budgetapp.R
import com.sardordev.budgetapp.databinding.CutomDialogBudgetBinding
import com.sardordev.budgetapp.databinding.FragmentExpenseBinding

class ExpenseFragment : Fragment() {
    private val binding by lazy { FragmentExpenseBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        return  binding.root
    }

}