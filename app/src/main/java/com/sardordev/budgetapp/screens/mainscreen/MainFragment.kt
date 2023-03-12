package com.sardordev.budgetapp.screens.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.sardordev.budgetapp.databinding.FragmentMainBinding
import com.sardordev.budgetapp.screens.StatisticFragment
import com.sardordev.budgetapp.screens.budgetscreen.BudgetFragment
import com.sardordev.budgetapp.screens.expensesreen.ExpenseFragment


class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val arrayFragment = ArrayList<Fragment>()
    private lateinit var pagerAdapter: MainViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        initViewPager()






        return binding.root
    }


    private fun initViewPager() {
        arrayFragment.add(BudgetFragment())
        arrayFragment.add(ExpenseFragment())
        arrayFragment.add(StatisticFragment())


        pagerAdapter = MainViewPagerAdapter(requireActivity(), arrayFragment)

        binding.viewpager.adapter = pagerAdapter
        binding.viewpager.isUserInputEnabled = false


        binding.btnexpense.setOnClickListener {
            if (binding.viewpager.currentItem != 2) {
                binding.viewpager.currentItem++
            }
        }
        binding.btnbudget.setOnClickListener {
            binding.viewpager.currentItem--
        }


        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.btnexpense.text = "Expense"
                        binding.btnbudget.text = "Budget"
                    }
                    1 -> {
                        binding.btnexpense.text = "Statistic"
                        binding.btnbudget.text = "Budget"
                    }
                    else -> {
                        binding.btnbudget.text = "Expense"
                    }
                }
            }
        })



    }


}


