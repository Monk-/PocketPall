package com.example.user.pocketpall.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.user.pocketpall.Classes.Categories;
import com.example.user.pocketpall.Classes.ExIn;
import com.example.user.pocketpall.Classes.Income;
import com.example.user.pocketpall.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.user.pocketpall.MainActivity.*;


public class Fragment2 extends Fragment {
    private View view;
    private PieChart mChart;
    private RadarChart rChart;
    private HorizontalBarChart bChart;
    private Spinner categ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void init()
    {
        mChart = (PieChart) view.findViewById(R.id.piechart);
        rChart = (RadarChart) view.findViewById(R.id.radarchart);
        bChart = (HorizontalBarChart) view.findViewById(R.id.barchart);
        categ = (Spinner)view.findViewById(R.id.periodSpinner);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        init();
        String[] items = Categories.getListStr();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);
        categ.setAdapter(adapter);

        Spinner typeChart = (Spinner)view.findViewById(R.id.chartSpinner);
        String[] items1 = new String[]{"PieChart", "RadarChart", "BarChart"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items1);
        typeChart.setAdapter(adapter1);
        typeChart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0:
                        PieChart();
                        rChart.setVisibility(View.GONE);
                        bChart.setVisibility(View.GONE);
                        break;
                    case 1:
                        RadarChart();
                        mChart.setVisibility(View.GONE);
                        bChart.setVisibility(View.GONE);
                        break;
                    case 2:
                        BarChart();
                        mChart.setVisibility(View.GONE);
                        rChart.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                PieChart();
            }

        });
        return view;
    }

    public void BarChart()
    {
        bChart.setVisibility(View.VISIBLE);
        bChart.setMaxVisibleValueCount(60);
        bChart.setPinchZoom(false);
        bChart.setDrawGridBackground(false);
        XAxis xAxis = bChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);


        YAxis leftAxis = bChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = bChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);

        ArrayList<String> xVals = new ArrayList<>();
        xVals = new ArrayList<String>(Arrays.asList(Categories.getListStr()));

        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        double g;
        for (int i=0;i< Categories.values().length;i++)
        {
            g = 0;
            for (ExIn inca :incDB.getCategoryAll(i))
            {
                g += inca.getAmount();
            }
            yVals1.add(new BarEntry((float)g,i));
          /*  g1 = 0;
            for (ExIn inca :expDB.getCategoryAll(i))
            {
                g1 += inca.getAmount();
            }
            entries2.add(new Entry((float)g1,i));*/
        }


        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
        set1.setBarSpacePercent(35f);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);

        bChart.setData(data);
    }

    public void RadarChart()
    {
        rChart.setVisibility(View.VISIBLE);
        rChart.setWebLineWidth(1.5f);
        rChart.setWebLineWidthInner(0.75f);
        rChart.setWebAlpha(100);
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries2 = new ArrayList<>();
        double g, g1;
        for (int i=0;i< Categories.values().length;i++)
        {
            g = 0;
            for (ExIn inca :incDB.getCategoryAll(i))
            {
                g += inca.getAmount();
            }
            entries.add(new Entry((float)g,i));
            g1 = 0;
            for (ExIn inca :expDB.getCategoryAll(i))
            {
                g1 += inca.getAmount();
            }
            entries2.add(new Entry((float)g1,i));
        }

        RadarDataSet dataset_comp1 = new RadarDataSet(entries, "Income");

        RadarDataSet dataset_comp2 = new RadarDataSet(entries2, "Expense");

        dataset_comp1.setColor(getResources().getColor(R.color.greenChart));

        dataset_comp2.setColor(getResources().getColor(R.color.redChart));

        dataset_comp1.setDrawFilled(true);

        dataset_comp2.setDrawFilled(true);

        ArrayList<RadarDataSet> kom = new ArrayList<>();
        kom.add(dataset_comp1);
        kom.add(dataset_comp2);
        RadarData data = new RadarData(Categories.getListStr(), kom);
        rChart.setData(data);
        rChart.invalidate();
    }

    public void PieChart()
    {
        mChart.setVisibility(View.VISIBLE);
        List<ExIn> inco = new ArrayList<>();
        for (int i=0;i< Categories.values().length;i++)
        {
            inco.addAll(incDB.getCategoryAll(i));
        }

        List<ExIn> expe = new ArrayList<>();
        for (int i=0;i< Categories.values().length;i++)
        {
            expe.addAll(expDB.getCategoryAll(i));
        }

        double j = 0.0;

        for (int i=0;i< inco.size();i++)
        {
            j += inco.get(i).getAmount();
        }


        double f = 0.0;

        for (int i=0;i< expe.size();i++)
        {
            f += expe.get(i).getAmount();
        }


        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        yVals1.add(new Entry((float)j, 0));
        yVals1.add(new Entry((float)f, 1));
        PieDataSet dataSet = new PieDataSet(yVals1, "Overall statistic");
        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Income");
        xVals.add("Expense");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.greenChart));
        colors.add(getResources().getColor(R.color.redChart));
        dataSet.setColors(colors);
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        mChart.setData(data);
        mChart.setUsePercentValues(true);
        mChart.invalidate();
    }
}
