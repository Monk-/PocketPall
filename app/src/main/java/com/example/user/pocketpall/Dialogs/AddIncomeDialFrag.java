package com.example.user.pocketpall.Dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.pocketpall.Classes.Income;
import com.example.user.pocketpall.ContextHelperClass;
import com.example.user.pocketpall.R;

import java.util.ArrayList;
import java.util.List;

import com.example.user.pocketpall.Classes.Categories;

import static com.example.user.pocketpall.MainActivity.date;
import static com.example.user.pocketpall.MainActivity.incDB;


public class AddIncomeDialFrag extends DialogFragment implements Command {
    public View dialogView;
    public AutoCompleteTextView actv;
    public List<String> names;

    public AddIncomeDialFrag() {
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        dialogView = inflater.inflate(R.layout.dial_frag_add_income, null);
        Spinner dropdown = (Spinner) dialogView.findViewById(R.id.IncategorySpinner);
        String[] items = new String[]{"Car", "Clothing", "Electronics", "Expenses", "Home", "Income", "Work", "Education", "Sports"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
         /*   List <User> users = db.getAllUsers();
            names = new ArrayList<String>();
            for (int i=0;i<users.size();i++)
            {
                String p = users.get(i).getName();
                names.add(p);
            }
            ArrayAdapter adapter = new ArrayAdapter(ContextHelperClass.getAppContext(),R.layout.my_list_item,names);
            actv = (AutoCompleteTextView)dialogView.findViewById(R.id.autoCompleteTextView);
            actv.setAdapter(adapter);*/


        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText title1 = (EditText) dialogView.findViewById(R.id.Intitle);
                        EditText comment1 = (EditText) dialogView.findViewById(R.id.Incomment);
                        EditText amount1 = (EditText) dialogView.findViewById(R.id.Inamount);
                        Spinner category1 = (Spinner) dialogView.findViewById(R.id.IncategorySpinner);
                        String title = title1.getText().toString();
                        String comment = comment1.getText().toString();
                        Double amount = Double.parseDouble(!amount1.getText().toString().equals("") ? amount1.getText().toString() : "-1.0");
                        Integer category = Categories.getInt(category1.getSelectedItem().toString());
                        boolean p = true;
                        if (title.equals("") || comment.equals("") || amount == -1 || category == -1 || date.equals("")) {
                            p = false;
                        }
                        if (p) {
                            incDB.addToDb(new Income(title, comment, amount, category, date));
                            //controllerInc.setList("Income", getActivity());
                            // db.addIncome(new Income(owner, name, mode, income, date));
                            //if (preferences.getString("Activity", "").equals("2")) {
                            //    checkDisOutNigga();
                            //}
                            //controllerInc.UpdateAdapterIncome(sumin, listview_in);
                            //Toast.makeText(ContextHelperClass.getAppContext(), "Income successfully added.", Toast.LENGTH_LONG).show();
                        } else {
                            //Toast.makeText(ContextHelperClass.getAppContext(), "There is no user with this name or there is other problem with data", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        Dialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    @Override
    public void execute(FragmentManager fg) {
        this.show(fg, "");
    }
}
