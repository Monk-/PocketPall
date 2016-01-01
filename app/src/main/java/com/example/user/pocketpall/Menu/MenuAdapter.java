package com.example.user.pocketpall.Menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.pocketpall.R;

import java.util.ArrayList;

/**
 * Created by User on 1/1/2016.
 */
public class MenuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MenuItom> menuItems;

    public MenuAdapter(Context context, ArrayList<MenuItom> navDrawerItems) {
        this.context = context;
        this.menuItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.menu_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        imgIcon.setImageResource(menuItems.get(position).getIcon());
        txtTitle.setText(menuItems.get(position).getTitle());
        return convertView;
    }
}
