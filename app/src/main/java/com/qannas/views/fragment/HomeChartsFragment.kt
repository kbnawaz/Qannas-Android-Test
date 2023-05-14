package com.qannas.views.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.qannas.R
import com.qannas.extension.ALL_TYPE
import com.qannas.extension.ONE_DAY
import com.qannas.extension.ONE_MONTH
import com.qannas.extension.ONE_WEEK
import com.qannas.extension.ONE_YEAR
import com.qannas.viewModel.HomeChartsViewModel
import kotlinx.android.synthetic.main.fragment_home_charts.view.all_ll
import kotlinx.android.synthetic.main.fragment_home_charts.view.day_ll
import kotlinx.android.synthetic.main.fragment_home_charts.view.month_ll
import kotlinx.android.synthetic.main.fragment_home_charts.view.week_ll
import kotlinx.android.synthetic.main.fragment_home_charts.view.year_ll


class HomeChartsFragment : Fragment() {


    private val homeChartsViewModel by lazy {
        ViewModelProvider(this)[HomeChartsViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_charts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeChartsViewModel.plotCharts(view, ONE_DAY, requireContext())

        view.day_ll.setOnClickListener {
            homeChartsViewModel.plotCharts(view, ONE_DAY, requireContext())
        }

        view.week_ll.setOnClickListener {
            homeChartsViewModel.plotCharts(view, ONE_WEEK, requireContext())
        }

        view.month_ll.setOnClickListener {
            homeChartsViewModel.plotCharts(view, ONE_MONTH, requireContext())
        }

        view.year_ll.setOnClickListener {
            homeChartsViewModel.plotCharts(view, ONE_YEAR, requireContext())
        }

        view.all_ll.setOnClickListener {
            homeChartsViewModel.plotCharts(view, ALL_TYPE, requireContext())
        }

    }


}