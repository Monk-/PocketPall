package com.example.user.pocketpall.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.pocketpall.List.ListItem;
import com.example.user.pocketpall.List.ListItemAdapter;
import com.example.user.pocketpall.R;

import java.util.ArrayList;


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
        ListItem john = new ListItem("John", 123.0, 0);

        ArrayList<Object> people = new ArrayList<>();
        people.add("Real People");
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add(john);
        people.add("Real People");
        people.add(john);
        people.add(john);
        people.add(john);

        listView.setAdapter(new ListItemAdapter(this, people));
        return view;

    }



}
