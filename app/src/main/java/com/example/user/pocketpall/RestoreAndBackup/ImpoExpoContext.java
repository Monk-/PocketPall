package com.example.user.pocketpall.RestoreAndBackup;

public class ImpoExpoContext
{
    private ImpoExpoStrategy impoExpoStrategy;

    public void setImpoExpoStrategy(ImpoExpoStrategy impoExpoStrategy)
    {
        this.impoExpoStrategy = impoExpoStrategy;
    }

    public void doIt()
    {
        impoExpoStrategy.moveDb();
    }
}