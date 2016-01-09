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

import com.example.user.pocketpall.ContextHelperClass;
import com.example.user.pocketpall.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.pocketpall.MainActivity.db;

public class AddExpenseDialFrag extends DialogFragment implements Command {
    public View dialogView;
    public AutoCompleteTextView actv;
    public List<String> names;

    public AddExpenseDialFrag()
    {}

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        dialogView = inflater.inflate(R.layout.dial_frag_add_expense, null);
        Spinner dropdown = (Spinner)dialogView.findViewById(R.id.categorySpinner);
        String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
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
                .setPositiveButton("Add", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {

                /*            EditText user1 = (EditText) dialogView.findViewById(R.id.autoCompleteTextView);
                            EditText name1 = (EditText) dialogView.findViewById(R.id.name);
                            EditText mode1 = (EditText) dialogView.findViewById(R.id.mode);
                            EditText income1 = (EditText) dialogView.findViewById(R.id.size);
                            String owner = user1.getText().toString();
                            String name = name1.getText().toString();
                            int mode = Integer.parseInt(mode1.getText().toString());
                            int income = Integer.parseInt(income1.getText().toString());
                            boolean p = false;
                            for (int i = 0; i < names.size(); i++)
                            {
                                String h = names.get(i).toString();
                                if (owner.equals(h))
                                {
                                    p = true;
                                }
                            }
                            if (owner.equals("") || name.equals("") || mode1.getText().toString().equals("") || income1.getText().toString().equals("") || date.equals(""))
                            {
                                p = false;
                            }
                            if (p == true)
                            {
                                incTemplate.addToDb(new Income(owner, name, mode, income, date));
                                controllerInc.setList("Income",getActivity());
                                // db.addIncome(new Income(owner, name, mode, income, date));
                                if (preferences.getString("Activity", "").equals("2"))
                                {
                                    checkDisOutNigga();
                                }
                                controllerInc.UpdateAdapterIncome(sumin,listview_in);
                                Toast.makeText(MyApplication.getAppContext(), "Income successfully added.", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(MyApplication.getAppContext(), "There is no user with this name or there is other problem with data", Toast.LENGTH_LONG).show();
                            }*/
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                });
        Dialog  dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    @Override
    public void execute(FragmentManager fg) {
        this.show(fg, "");
    }
}
