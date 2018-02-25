package me.developeralfa.calendoer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by devalfa on 18/2/18.
 */

public class TodoOpenHandler extends SQLiteOpenHelper {
    public TodoOpenHandler(Context context) {
        super(context, "todo_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String makeTableForTasks = "CREATE TABLE " + Constants.Tasks.TABLE_NAME + " ( "+
                Constants.Tasks.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Constants.Tasks.TASK + " TEXT, "+
                Constants.Tasks.DESCRIPTION + " TEXT,"+
                Constants.Tasks.DATEADDED +" DATE," +
                Constants.Tasks.DATEDUE + " DATE);";
        db.execSQL(makeTableForTasks);
        String makeTableForComments = "CREATE TABLE " + Constants.Notes.TABLE_NAME + " ( "+
                Constants.Notes.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Constants.Notes.TASK_ID + " INTEGER, "+
                Constants.Notes.DESCRIPTION + " TEXT,"+
                " FOREIGN KEY(" + Constants.Notes.TASK_ID + ") REFERENCES "+ Constants.Tasks.TABLE_NAME+"("+Constants.Tasks.ID+");";
        db.execSQL(makeTableForComments);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
