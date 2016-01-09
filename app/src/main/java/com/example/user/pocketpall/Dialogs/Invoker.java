package com.example.user.pocketpall.Dialogs;

import android.app.FragmentManager;


public class Invoker {
    private Command command;
    private FragmentManager fg;

    public FragmentManager getFg()
    {
        return fg;
    }

    public void setFg(FragmentManager fg)
    {

        this.fg = fg;
    }

    public void setCommand(Command command)
    {
        this.command = command;
    }

    public void show()
    {
        command.execute(fg);
    }

}
