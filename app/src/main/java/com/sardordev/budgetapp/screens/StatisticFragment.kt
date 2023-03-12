package com.sardordev.budgetapp.screens

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.sardordev.budgetapp.R
import com.sardordev.budgetapp.data.model.BudgetEntity
import com.sardordev.budgetapp.databinding.FragmentStatisticBinding
import com.sardordev.budgetapp.screens.budgetscreen.BudgetViewModel


class StatisticFragment : Fragment() {
    private val viewmodelBudget by viewModels<BudgetViewModel>()
    private val binding by lazy {
        FragmentStatisticBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewmodelBudget.getData.observe(viewLifecycleOwner, Observer {
            getChart(it)
        })


        return binding.root
    }


    private fun getChart(it: List<BudgetEntity>) {
        val plist = ArrayList<PieEntry>()
        for (i in 0 until it.size) {
            plist.add(PieEntry(it.get(i).amountBudgets.toFloat(), "${it.get(i).categoryName}"))
        }
        val pieDataSet = PieDataSet(plist, "")
        val piedata = PieData(pieDataSet)
        pieDataSet.setColors(Color.GREEN)
        binding.piechartBudget.data = piedata

        Log.d("PPP", plist.toString())
    }


}