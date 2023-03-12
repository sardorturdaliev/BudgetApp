package com.sardordev.budgetapp.screens.expensesreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sardordev.budgetapp.R
import com.sardordev.budgetapp.data.model.BudgetEntity
import com.sardordev.budgetapp.data.model.ExpenceEntity
import com.sardordev.budgetapp.databinding.CustomDialogExpenceBinding
import com.sardordev.budgetapp.databinding.CutomDialogBudgetBinding
import com.sardordev.budgetapp.databinding.FragmentExpenseBinding
import com.sardordev.budgetapp.screens.budgetscreen.BudgetViewModel
import com.sardordev.budgetapp.screens.expensesreen.adapter.ExpenseAdapter
import kotlin.math.log

class ExpenseFragment : Fragment() {
    private val binding by lazy { FragmentExpenseBinding.inflate(layoutInflater) }
    private val viewModelBudget by viewModels<BudgetViewModel>()
    private val viewModel by viewModels<ExpenceViewModel>()
    private lateinit var arrayAdapterSpinnerAdapter: ArrayAdapter<String>
    private val expenseAdapter = ExpenseAdapter()
    private lateinit var listBudget: ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.addImg.setOnClickListener {
            initCusdialog()
        }

        initrecycler()

        initAmountExpence()

        return binding.root
    }

    private fun initCusdialog() {
        val alert = BottomSheetDialog(requireContext())
        val view = CustomDialogExpenceBinding.inflate(layoutInflater)
        alert.setContentView(view.root)
        val dialog = alert.show()

        //set Spinner
        listBudget = ArrayList()
        viewModelBudget.getData.observe(viewLifecycleOwner, Observer {
            for (i in it.indices) {
                listBudget.add(it[i].categoryName)
            }
        })
        arrayAdapterSpinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listBudget)
        view.spinnerBudgets.setAdapter(arrayAdapterSpinnerAdapter)



        view.bntSave.setOnClickListener {
            if (view.edamount.text!!.isNotEmpty() && view.spinnerBudgets.text.isNotEmpty()) {
                //save Data
                viewModel.insert(
                    ExpenceEntity(
                        0,
                        view.spinnerBudgets.text.toString(),
                        view.edamount.text.toString().toFloat()
                    )
                )
                expenseAdapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "Saved Data", Toast.LENGTH_SHORT).show()
                alert.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please Enter", Toast.LENGTH_SHORT).show()
            }


        }


    }

    private fun initrecycler() {
        viewModel.getExpence.observe(viewLifecycleOwner, Observer {
            expenseAdapter.submitList(it)

            binding.rvexpense.adapter = expenseAdapter
            binding.rvexpense.layoutManager = LinearLayoutManager(requireContext())
            binding.rvexpense.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )



        })
    }

    private fun initAmountExpence() {
        viewModel.getAmountAllExpence.observe(viewLifecycleOwner, Observer {
// get Data only amount column : expenceAmount=3000.0
            var sum = 0
            Log.d("QQQ", it.toString())
            for (i in it.indices) {
                sum += it[i].expenceAmount.toInt()
            }
            binding.tvallexpence.text = sum.toString()
        })
    }


}