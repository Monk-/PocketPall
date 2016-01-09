package com.example.user.pocketpall;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.devspark.appmsg.AppMsg;
import com.example.user.pocketpall.Database.DatabaseHandler;
import com.example.user.pocketpall.Dialogs.AddExpenseDialFrag;
import com.example.user.pocketpall.Dialogs.AddIncomeDialFrag;
import com.example.user.pocketpall.Dialogs.Command;
import com.example.user.pocketpall.Dialogs.DeleteExpenseDialFrag;
import com.example.user.pocketpall.Dialogs.DeleteIncomeDialFrag;
import com.example.user.pocketpall.Dialogs.Invoker;
import com.example.user.pocketpall.Fragments.PagerAdapter;
import com.example.user.pocketpall.Menu.MenuAdapter;
import com.example.user.pocketpall.Menu.MenuItom;
import com.example.user.pocketpall.RestoreAndBackup.ExpoStrategy;
import com.example.user.pocketpall.RestoreAndBackup.ImpoExpoContext;
import com.example.user.pocketpall.RestoreAndBackup.ImpoStrategy;


import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private ArrayList<MenuItom> menuItems;
    private MenuAdapter menuAdapter;

    private Toolbar toolbar;
    private TabLayout tabLayout;

    public static  String date =""; //date picker result

    public static DatabaseHandler db;

    private ImpoExpoContext ctx; // import and export


    public static Invoker invoker;
    public static Command addIncomeDialFrag;
    public static Command addExpenseDialFrag;
    public static Command deleteIncomeDialFrag;
    public static Command deleteExpenseDialFrag;
    public static Command decor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        initBase();
        setDialogs();
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


        addDrawerItems();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        invoker.setCommand(addIncomeDialFrag);
                        invoker.show();
                        break;
                    case 1:
                        invoker.setCommand(addExpenseDialFrag);
                        invoker.show();
                        break;
                    case 2:
                        ctx.setImpoExpoStrategy(new ExpoStrategy());
                        ctx.doIt();
                        AppMsg.makeText(MainActivity.this, "Backup created", new AppMsg.Style(2000, R.color.green)).show();
                        break;
                    case 3:
                        ctx.setImpoExpoStrategy(new ImpoStrategy());
                        ctx.doIt();
                        AppMsg.makeText(MainActivity.this, "Data restored", new AppMsg.Style(2000, R.color.green)).show();
                        break;
                }
            }
        });
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        setupDrawer();
    }

    private void initBase()
    {
        db = new DatabaseHandler(getApplicationContext());
    }

    private void initVars()
    {
        ctx = new ImpoExpoContext();
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        settingUpToolbar();
        tabLayout = initTabs(); // initialising tabs
    }

    private void settingUpToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private TabLayout initTabs()
    {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        return tabLayout;
    }

    private void setDialogs()
    {
        invoker = new Invoker();
        invoker.setFg(getFragmentManager());
        addIncomeDialFrag = new AddIncomeDialFrag();
        addExpenseDialFrag = new AddExpenseDialFrag();
        //deleteIncomeDialFrag = new DeleteIncomeDialFrag();
        //deleteExpenseDialFrag = new DeleteExpenseDialFrag();

    }

    private void addDrawerItems() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItom("Add Income", R.drawable.income));
        menuItems.add(new MenuItom("Add Expence", R.drawable.expence));
        menuItems.add(new MenuItom("Create backup", R.drawable.save));
        menuItems.add(new MenuItom("Restore backup", R.drawable.restore));
        menuAdapter = new MenuAdapter(getApplicationContext(),
                menuItems);
        mDrawerList.setAdapter(menuAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity2_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            date = Integer.toString(day) + ":"+
                    Integer.toString(month) + ":" +
                    Integer.toString(year);
        }


    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

}
