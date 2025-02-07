package com.newposandroid.presentation.main.report

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentReportBinding
import com.newposandroid.domain.model.SellingProduct

class ReportFragment : BaseFragment<FragmentReportBinding>() {
    private val sellingProductAdapter by lazy { SellingProductAdapter() }

    private val sellingProducts: MutableList<SellingProduct> = mutableListOf(
        SellingProduct(
            name = "Wagyu A5",
            price = "$50",
            percentage = 40,
            sales = 40,
        ),
        SellingProduct(
            name = "Wagyu A5",
            price = "$50",
            percentage = 40,
            sales = 40,
        ),
    )

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportBinding = FragmentReportBinding.inflate(inflater, container, false)

    override suspend fun FragmentReportBinding.setupEvent() {
    }

    override suspend fun FragmentReportBinding.setupState() {


    }


    override fun FragmentReportBinding.initBinding() {
        renderContent()
        renderProgressBar()
        renderLineChart()
    }

    private fun FragmentReportBinding.renderContent() {

    }

    private fun FragmentReportBinding.renderProgressBar() {
        sellingProductAdapter.setItems(sellingProducts)

        rvSellingProduct.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = sellingProductAdapter
        }
    }

    private fun FragmentReportBinding.renderLineChart() {
        // Sample data
        val entries = listOf(
            Entry(0f, 20f), // Monday
            Entry(1f, 50f), // Tuesday
            Entry(2f, 112f), // Wednesday
            Entry(3f, 70f), // Thursday
            Entry(4f, 30f), // Friday
            Entry(5f, 10f), // Saturday
            Entry(6f, 0f)  // Sunday
        )

        val lineDataSet = LineDataSet(entries, "Transactions")
        lineDataSet.color = Color.BLUE
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.circleRadius = 5f
        lineDataSet.setCircleColor(Color.BLUE)
        lineDataSet.lineWidth = 2f
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER // For smooth curves

        val lineData = LineData(lineDataSet)
        lineChart.data = lineData

        // Customize the chart
        lineChart.axisRight.isEnabled = false // Disable right axis
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.valueFormatter = XAxisFormatter()

        lineChart.description = Description().apply {
            text = ""
        }

        lineChart.invalidate() // Refresh the chart
    }
}

private // Custom XAxis formatter for days of the week
class XAxisFormatter : com.github.mikephil.charting.formatter.ValueFormatter() {
    private val days = listOf("M", "T", "W", "T", "F", "S", "S")
    override fun getFormattedValue(value: Float): String {
        return days[value.toInt()]
    }
}