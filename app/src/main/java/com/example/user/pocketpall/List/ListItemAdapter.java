package com.example.user.pocketpall.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.example.user.pocketpall.Fragments.Fragment1;
import com.example.user.pocketpall.R;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter implements ListAdapter {
    private ArrayList<Object> personArray;
    private LayoutInflater inflater;
    private static final int TYPE_PERSON = 0;
    private static final int TYPE_DIVIDER = 1;

    public ListItemAdapter(Fragment1 fragment1, ArrayList<Object> people) {
        this.personArray = people;
        this.inflater = (LayoutInflater)fragment1.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return (getItemViewType(position) == TYPE_PERSON);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return personArray.size();
    }

    @Override
    public Object getItem(int position) {
        return personArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_PERSON:
                    convertView = inflater.inflate(R.layout.row_item, parent, false);
                    break;
                case TYPE_DIVIDER:
                    convertView = inflater.inflate(R.layout.row_header, parent, false);
                    break;
            }
        }

        switch (type) {
            case TYPE_PERSON:
              /*  Person person = (Person)getItem(position);
                TextView name = (TextView)convertView.findViewById(R.id.nameLabel);
                TextView address = (TextView)convertView.findViewById(R.id.addressLabel);
                name.setText(person.getName());
                address.setText(person.getAddress());*/
                break;
            case TYPE_DIVIDER:
               /* TextView title = (TextView)convertView.findViewById(R.id.headerTitle);
                String titleString = (String)getItem(position);
                title.setText(titleString);*/
                break;
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof ListItem) {
            return TYPE_PERSON;
        }

        return TYPE_DIVIDER;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
