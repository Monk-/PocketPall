package com.example.user.pocketpall.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.pocketpall.Classes.Categories;
import com.example.user.pocketpall.Classes.ExIn;
import com.example.user.pocketpall.Classes.Expense;
import com.example.user.pocketpall.Classes.Income;
import com.example.user.pocketpall.List.ListItem;
import com.example.user.pocketpall.List.ListItemAdapter;
import com.example.user.pocketpall.R;
import com.example.user.pocketpall.StatisticList.ExpandableHeightListView;
import com.example.user.pocketpall.StatisticList.StatisticAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static com.example.user.pocketpall.MainActivity.*;
public class Fragment3 extends Fragment {

    private static View view;
    private static Fragment3 fragment3;
    private List<ExpandableHeightListView>  listso;
    private List <LinearLayout> linear;
    private Spinner spinner;

    public Fragment3() {
        fragment3 = this;

    }

    void initListViews()
    {
        listso = new ArrayList<>();
        linear = new ArrayList<>();
        this.listso.add((ExpandableHeightListView) view.findViewById(R.id.Car));
        this.listso.add((ExpandableHeightListView)view.findViewById(R.id.Clothing));
        this.listso.add((ExpandableHeightListView)view.findViewById(R.id.Electronics));
        this.listso.add((ExpandableHeightListView)view.findViewById(R.id.Expenses));
        this.listso.add((ExpandableHeightListView)view.findViewById(R.id.Home));
        this.listso.add((ExpandableHeightListView)view.findViewById(R.id.Income));
        this.listso.add((ExpandableHeightListView)view.findViewById(R.id.Work));
        this.listso.add((ExpandableHeightListView)view.findViewById(R.id.Education));
        this.listso.add((ExpandableHeightListView) view.findViewById(R.id.Sports));
        for (ExpandableHeightListView lv: listso)
        {
            lv.setExpanded(true);
        }
        this.linear.add((LinearLayout) view.findViewById(R.id.CarLin));
        this.linear.add((LinearLayout)view.findViewById(R.id.ClothingLin));
        this.linear.add((LinearLayout)view.findViewById(R.id.ElectronicsLin));
        this.linear.add((LinearLayout)view.findViewById(R.id.ExpensesLin));
        this.linear.add((LinearLayout)view.findViewById(R.id.HomeLin));
        this.linear.add((LinearLayout)view.findViewById(R.id.IncomeLin));
        this.linear.add((LinearLayout)view.findViewById(R.id.WorkLin));
        this.linear.add((LinearLayout)view.findViewById(R.id.EducationLin));
        this.linear.add((LinearLayout) view.findViewById(R.id.SportsLin));
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);
        init();
        setAdapter();
        initListViews();
        List<ExIn> exIns;
        String cat = "";
        List<Object> come;
        Double sumAll = 0.0;
        for (int i = 0;i < Categories.values().length; i++)
        {
            come = new ArrayList<>();
            cat = Categories.getStr(i);
            exIns = incDB.getCategoryAll(i);
            exIns.addAll(expDB.getCategoryAll(i));
            Collections.sort(exIns, new LexicographicComparator());
            double sum = 0;
            for (ExIn exIn: exIns)
            {
                if (exIn instanceof Income)
                {
                    sum += exIn.getAmount();
                }
                else if (exIn instanceof Expense)
                {
                    sum -= exIn.getAmount();
                }
            }
            sumAll += sum;
            if (exIns.size() > 0)
            {
                come.add(cat);
                come.addAll(exIns);
                listso.get(i).setAdapter(new StatisticAdapter(fragment3, come, sum));
                linear.get(i).setVisibility(View.VISIBLE);
                linear.get(i).setPadding(10, 20, 20, 20);
            }
            else
            {
                linear.get(i).setVisibility(View.GONE);
                linear.get(i).setPadding(0,0,0,0);
            }

        }
        TextView textView = (TextView)view.findViewById(R.id.greenLineText);
        textView.setText("Balance: " + sumAll.toString() + " $");
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.greenLine);
        if (sumAll < 0)
        {
            linearLayout.setBackgroundColor(Color.parseColor("#E57373"));
        }
        return view;
    }

    void init()
    {
        spinner = (Spinner)view.findViewById(R.id.incExpIncExp);
    }

    void setAdapter()
    {
        String[] items = new String[] {"All", "Income", "Expense"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    static class LexicographicComparator implements Comparator<ExIn> {

        @Override
        public int compare(ExIn lhs, ExIn rhs) {
            if (lhs.getAmount() > rhs.getAmount())
            {
                return -1;
            }
            else if(lhs.getAmount() < rhs.getAmount())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

}
