package com.example.user.pocketpall.RestoreAndBackup;

import android.os.Environment;
import android.widget.Toast;

import com.example.user.pocketpall.ContextHelperClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class ExpoStrategy implements ImpoExpoStrategy
{
    @Override
    public void moveDb()
    {

        try
        {
            File direct = new File(Environment.getExternalStorageDirectory() + "/BackupFolder");

            if (!direct.exists())
            {
                if (direct.mkdir())
                {
                    //directory is created;
                }

            }
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite())
            {
                String currentDBPath = "//data//" + "com.example.Project"
                        + "//databases//" + "BudgetDB";
                String backupDBPath = "/BackupFolder/BudgetDB";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(ContextHelperClass.getAppContext(), backupDB.toString(),
                        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e)
        {

            String l = e.toString();
            Toast.makeText(ContextHelperClass.getAppContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
}