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
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sardordev.budgetapp.R
import com.sardordev.budgetapp.data.dao.BudgetDao
import com.sardordev.budgetapp.data.db.MyDatabase
import com.sardordev.budgetapp.data.model.AmountData
import com.sardordev.budgetapp.data.model.AmountDataExpence
import com.sardordev.budgetapp.data.model.BudgetEntity
import com.sardordev.budgetapp.databinding.CutomDialogBudgetBinding
import com.sardordev.budgetapp.databinding.FragmentBudgetBinding
import com.sardordev.budgetapp.screens.budgetscreen.adapter.BudgetAdapter
import com.sardordev.budgetapp.screens.expensesreen.ExpenceViewModel
import java.text.NumberFormat
import kotlin.math.log


class BudgetFragment : Fragment(), BudgetAdapter.ClickListenerItem {
    private val binding by lazy { FragmentBudgetBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<BudgetViewModel>()
    private val viewModelExpense by viewModels<ExpenceViewModel>()
    private val budgetAdapter = BudgetAdapter(this)
    private lateinit var budgetDao: BudgetDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        budgetDao = MyDatabase.getintance().getBudggetDao()

        //init recyclerView withadapter
        initRecycler()


        //floatActionButton
        settingFloating()


        //init All Budget
        initAmountBudget()


        //init Amount Expenseen
        getAmountExpences()

        initRemaining()


        return binding.root
    }


    private fun initCusdialog() {
        val alert = BottomSheetDialog(requireContext())
        val view = CutomDialogBudgetBinding.inflate(layoutInflater)
        alert.setContentView(view.root)
        val dialog = alert.show()




        view.bntSave.setOnClickListener {
            if (view.edbudgetname.text!!.isNotEmpty() && view.edamount.text!!.isNotEmpty()) {
                //all data saved
                viewModel.insert(
                    BudgetEntity(
                        0,
                        view.edbudgetname.text.toString(),
                        view.edamount.text.toString().toInt()
                    )
                )
                Toast.makeText(requireContext(), "Saved ", Toast.LENGTH_SHORT).show()
                alert.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please Enter Data", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun getAmountExpences() {
        viewModelExpense.getAmountAllExpence.observe(viewLifecycleOwner, Observer {
            val allexpense = findSumExpense(it)
            binding.tvTotalbudgetExpense.text = allexpense.toString()
        })
    }


    private fun initRecycler() {
        viewModel.getData.observe(viewLifecycleOwner, Observer {
            budgetAdapter.submitList(it)
            binding.rvbudgets.adapter = budgetAdapter
            binding.rvbudgets.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvbudgets.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        binding.floating.hide()
                    } else {
                        binding.floating.show()
                    }
                }
            })
        })
    }

    private fun settingFloating() {
        binding.floating.setOnClickListener {
            initCusdialog()
        }

    }

    private fun initAmountBudget() {
        viewModel.getAmountBudget.observe(viewLifecycleOwner, Observer {
            val sum = findSumBudget(it)
            binding.tvTotalbudget.text = formatNumber(sum)
            Log.d("amount", sum.toString())
        })
    }

    private fun findSumBudget(list: List<AmountData>): Int {
        var sum = 0
        for (i in list.indices) {
            sum += list[i].amountBudgets.toInt()
        }
        return sum
    }

    private fun findSumExpense(list: List<AmountDataExpence>): Int {
        var sum = 0

        for (i in list.indices) {
            sum += list[i].expenceAmount.toInt()
        }
        return sum
    }


    private fun initRemaining() {
        viewModel.getAmountBudget.observe(viewLifecycleOwner, Observer {
            val sumbudget = findSumBudget(it)

            viewModelExpense.getAmountAllExpence.observe(viewLifecycleOwner, Observer {

                val allexpense = findSumExpense(it)
                val sum = sumbudget - allexpense
                binding.tvTotalbudgetRemaining.text = formatNumber(sum)

            })
        })
    }

    override fun clickItem(budgetEntity: BudgetEntity) {
        val alert = BottomSheetDialog(requireContext())
        val view = CutomDialogBudgetBinding.inflate(layoutInflater)
        alert.setContentView(view.root)
        val dialog = alert.show()

        view.edbudgetname.setText(budgetEntity.categoryName)
        view.edamount.setText(budgetEntity.amountBudgets.toString())



        view.bntSave.setOnClickListener {
            if (view.edbudgetname.text!!.isNotEmpty() && view.edamount.text!!.isNotEmpty() && view.edamount.text!!.isDigitsOnly()) {
                //all data saved
                budgetDao.update(view.edbudgetname.text.toString(), view.edamount.text.toString(), budgetEntity.id)
                Toast.makeText(requireContext(), "Saved ", Toast.LENGTH_SHORT).show()
                alert.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please Enter Data", Toast.LENGTH_SHORT).show()
            }
        }


    }



    private fun formatNumber(num: Int): String {
        return NumberFormat.getInstance().format(num)
    }



}


