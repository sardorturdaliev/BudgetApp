package com.sardordev.budgetapp.screens.budgetscreen

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sardordev.budgetapp.R
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.BudgetEntity
import com.sardordev.budgetapp.databinding.CutomDialogBudgetBinding
import com.sardordev.budgetapp.databinding.FragmentBudgetBinding
import com.sardordev.budgetapp.screens.budgetscreen.adapter.BudgetAdapter
import kotlin.math.log


class BudgetFragment : Fragment() {
    private val binding by lazy { FragmentBudgetBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<BudgetViewModel>()
    private val budgetAdapter = BudgetAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.floating.setOnClickListener {
            initCusdialog()
        }



        viewModel.getData.observe(viewLifecycleOwner, Observer {
            budgetAdapter.submitList(it)
            binding.rvbudgets.adapter = budgetAdapter
            binding.rvbudgets.layoutManager = GridLayoutManager(requireContext(), 2)
            getChart(it)
        })




        viewModel.getAmountBudget.observe(viewLifecycleOwner, Observer {
            var sum = 0
            var list = ArrayList<AmountData>()
            //qiymatlar kelyapti listga qoshvoldik [3000,7000,2000]
            list.addAll(it)

            for (i in 0 until list.size) {
                sum += list[i].amountBudgets
            }
            Log.d("TTT", sum.toString())
            binding.tvTotalbudget.text = sum.toString()
        })




        return binding.root
    }


    private fun initCusdialog() {
        val alert = BottomSheetDialog(requireContext())
        val view = CutomDialogBudgetBinding.inflate(layoutInflater)
        alert.setContentView(view.root)
        val dialog = alert.show()


        view.bntSave.setOnClickListener {
            if (view.edbudgetname.text!!.isNotEmpty() && view.edamount.text!!.isNotEmpty() && view.edamount.text!!.isDigitsOnly()) {
                //all data saved
                viewModel.insert(
                    BudgetEntity(
                        0,
                        view.edbudgetname.text.toString(),
                        view.edamount.text.toString().toFloat()
                    )
                )
                Toast.makeText(requireContext(), "Saved ", Toast.LENGTH_SHORT).show()
                alert.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please Enter Data", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun getChart(it: List<BudgetEntity>) {
        val plist = ArrayList<PieEntry>()

        for (i in 0 until it.size) {
            plist.add(PieEntry(it.get(i).amountBudgets, "${it.get(i).categoryName}"))
        }
        val pieDataSet = PieDataSet(plist, "")
        val piedata = PieData(pieDataSet)
        pieDataSet.setColors(Color.GREEN)
        binding.piechartBudget.data = piedata

        Log.d("PPP", plist.toString())
    }

}


