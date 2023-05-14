package com.qannas.viewModel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.renderer.XAxisRenderer
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Transformer
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler
import com.qannas.R
import com.qannas.extension.allData
import com.qannas.extension.dailyHours
import com.qannas.extension.getAllData
import com.qannas.extension.getDailyHoursData
import com.qannas.extension.getMonthlyData
import com.qannas.extension.getWeeklyData
import com.qannas.extension.getYearlyData
import com.qannas.extension.monthlyDays
import com.qannas.extension.weeklyDays
import com.qannas.extension.yearlyMonths
import kotlinx.android.synthetic.main.fragment_home_charts.view.all_ll
import kotlinx.android.synthetic.main.fragment_home_charts.view.chart1
import kotlinx.android.synthetic.main.fragment_home_charts.view.day_ll
import kotlinx.android.synthetic.main.fragment_home_charts.view.filter_range_txt
import kotlinx.android.synthetic.main.fragment_home_charts.view.month_ll
import kotlinx.android.synthetic.main.fragment_home_charts.view.week_ll
import kotlinx.android.synthetic.main.fragment_home_charts.view.year_ll

class HomeChartsViewModel : ViewModel() {


    fun plotCharts(view: View, isType: Int, context: Context) {

        view.chart1.description.isEnabled = false
        view.chart1.setTouchEnabled(true)
        view.chart1.dragDecelerationFrictionCoef = 0.9f
        view.chart1.isDragEnabled = true
        view.chart1.setScaleEnabled(true)
        view.chart1.setDrawGridBackground(false)
        view.chart1.isHighlightPerDragEnabled = true
        view.chart1.setPinchZoom(true)
        view.chart1.setBackgroundColor(Color.WHITE)
        setData(
            view,
            dataList(view, isType, context),
            context
        )
        view.chart1.animateX(1500)
        val l: Legend = view.chart1.legend
        l.form = Legend.LegendForm.LINE
        l.textSize = 11f
        l.textColor = Color.LTGRAY
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        val xAxis: XAxis = view.chart1.xAxis
        xAxis.textSize = 11f
        xAxis.textColor = Color.LTGRAY
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        xAxis.enableGridDashedLine(10f, 10f, 4f)

        val leftAxis: YAxis = view.chart1.axisLeft
        leftAxis.textColor = Color.LTGRAY
        leftAxis.textSize = 11f
        leftAxis.axisMaximum = 5f
        leftAxis.axisMinimum = -5f
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.granularity = 2.5f
        leftAxis.enableGridDashedLine(10f, 10f, 2f)

        view.chart1.xAxis.position = XAxis.XAxisPosition.BOTTOM
        val description = Description()
        description.text = ""
        view.chart1.description = description    // Hide the description
        view.chart1.axisRight.setDrawLabels(false)
        view.chart1.axisLeft.setDrawGridLines(true)
        view.chart1.axisRight.setDrawGridLines(false)
        view.chart1.axisRight.enableGridDashedLine(10f, 10f, 4f)
        view.chart1.axisLeft.enableGridDashedLine(10f, 10f, 4f)

        view.chart1.xAxis.setDrawGridLines(true)
        view.chart1.xAxis.setCenterAxisLabels(true)
        view.chart1.xAxis.valueFormatter = MyXAxisFormatter(isType)
        view.chart1.axisLeft.valueFormatter = MyYAxisFormatter()
        view.chart1.setXAxisRenderer(
            CustomXAxisRenderer(
                view.chart1.viewPortHandler, view.chart1.xAxis, view.chart1.getTransformer(
                    YAxis.AxisDependency.RIGHT
                )
            )
        )

        view.chart1.extraBottomOffset = 50f
        view.chart1.legend.isEnabled = false
    }

    private fun dataList(view: View, isType: Int, context: Context): ArrayList<Entry> {
        return when (isType) {
            0 -> {
                view.day_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_selected)
                view.week_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.month_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.year_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.all_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.filter_range_txt.text = context.getString(R.string.zero_to_twenty_four_hrs)
                getDailyHoursData()
            }

            1 -> {
                view.day_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.week_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_selected)
                view.month_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.year_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.all_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.filter_range_txt.text = context.getString(R.string.mon_to_sun)
                getWeeklyData()
            }

            2 -> {
                view.day_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.week_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.month_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_selected)
                view.year_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.all_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.filter_range_txt.text = context.getString(R.string.sep_one_to_thirty)

                getMonthlyData()
            }

            3 -> {
                view.day_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.week_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.month_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.year_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_selected)
                view.all_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.filter_range_txt.text = context.getString(R.string.jan_to_dec)
                getYearlyData()
            }

            4 -> {
                view.day_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.week_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.month_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.year_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_unselected)
                view.all_ll.background =
                    ContextCompat.getDrawable(context, R.drawable.corner_shape_selected)
                view.filter_range_txt.text = context.getString(R.string.twenty_twenty_three_to)
                getAllData()
            }

            else -> {
                getDailyHoursData()
            }
        }
    }

    private fun setData(view: View, values1: ArrayList<Entry>, context: Context) {

        val set1: LineDataSet
        if (view.chart1.data != null &&
            view.chart1.data.dataSetCount > 0
        ) {
            set1 = view.chart1.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values1
            view.chart1.data.notifyDataChanged()
            view.chart1.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values1, "DataSet")
            set1.axisDependency = YAxis.AxisDependency.LEFT
            set1.color = ContextCompat.getColor(context, R.color.green)
            set1.setCircleColor(Color.WHITE)
            set1.lineWidth = 3f
            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.fillAlpha = 65
            set1.fillColor = ColorTemplate.getHoloBlue()
            set1.setDrawCircles(false)
            val data = LineData(set1)
            data.setValueTextColor(Color.WHITE)
            data.setValueTextSize(9f)
            data.setDrawValues(false)
            view.chart1.data = data
        }
    }

    class MyXAxisFormatter(private val isType: Int) : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return when (isType) {
                0 -> dailyHours.getOrNull((value - 1).toInt()).toString()
                1 -> weeklyDays.getOrNull((value - 1).toInt())
                    .toString()

                2 -> monthlyDays.getOrNull((value - 1).toInt())
                    .toString()

                3 -> yearlyMonths.getOrNull((value - 1).toInt()).toString()
                4 -> allData.getOrNull((value - 1).toInt()).toString()
                else -> value.toString()
            }
        }
    }

    class MyYAxisFormatter : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return "$value%"
        }
    }

    class CustomXAxisRenderer(
        viewPortHandler: ViewPortHandler?,
        xAxis: XAxis?,
        trans: Transformer?
    ) :
        XAxisRenderer(viewPortHandler, xAxis, trans) {
        override fun drawLabel(
            c: Canvas,
            formattedLabel: String,
            x: Float,
            y: Float,
            anchor: MPPointF,
            angleDegrees: Float
        ) {
            val line = formattedLabel.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            Utils.drawXAxisValue(
                c,
                line[0], x, y, mAxisLabelPaint, anchor, angleDegrees
            )
            if (line.size > 1)
                Utils.drawXAxisValue(
                    c,
                    line[1],
                    x,
                    y + mAxisLabelPaint.textSize,
                    mAxisLabelPaint,
                    anchor,
                    angleDegrees
                )
        }
    }


}