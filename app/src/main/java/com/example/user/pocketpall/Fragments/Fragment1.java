package com.example.user.pocketpall.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.pocketpall.Classes.ExIn;
import com.example.user.pocketpall.Classes.Income;
import com.example.user.pocketpall.List.ListItem;
import com.example.user.pocketpall.List.ListItemAdapter;
import com.example.user.pocketpall.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.example.user.pocketpall.MainActivity.*;

public class Fragment1 extends Fragment {

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment1, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listViewMainFrag);
        List<ExIn> comes = incDB.getAllComes();
        comes.addAll(expDB.getAllComes());
        Collections.sort(comes, new LexicographicComparator());
        List<Object> come = new ArrayList<>();
        int curMonth = 0;
        int month;
        if (comes.size() > 0) {
            if (Integer.parseInt((comes.get(0)).getMonth()) == 0) {
                come.add(getMonth(curMonth));
            }
            for (int i = 0; i < comes.size(); i++) {
                month = Integer.parseInt((comes.get(i)).getMonth());
                if (month > curMonth) {
                    curMonth = month;
                    come.add(getMonth(curMonth));
                }
                come.add(comes.get(i));

            }
            listView.setAdapter(new ListItemAdapter(this, come));
        }
        return view;

    }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    class LexicographicComparator implements Comparator<ExIn> {

        @Override
        public int compare(ExIn lhs, ExIn rhs) {
            String[] dateL = lhs.getDate().split(":");
            String[] dateR = rhs.getDate().split(":");
            if (Integer.parseInt(dateL[1]) > Integer.parseInt(dateR[1]) )
            {
                return 1;
            }
            else if(Integer.parseInt(dateL[1]) == Integer.parseInt(dateR[1]))
            {
                if(Integer.parseInt(dateL[0]) > Integer.parseInt(dateR[0]))
                {
                    return 1;
                }
                else if(Integer.parseInt(dateL[0]) == Integer.parseInt(dateR[0]))
                {
                    return 0;
                }
                else
                {
                    return -1;
                }
            }
            else
            {
                return -1;
            }
        }
    }

}
