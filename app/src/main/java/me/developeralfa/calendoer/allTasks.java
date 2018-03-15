package me.developeralfa.calendoer;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;

public class allTasks extends AppCompatActivity implements infoClickListener,restoreClickListener,recurClickListener,deleteClickListener,doneClickListener,editClickListener {
    ArrayList<String> pending = new ArrayList<String>();
    ArrayList<String> descpending = new ArrayList<String>();
    ArrayList<String> done = new ArrayList<String>();
    ArrayList<String> descdone = new ArrayList<String>();
    ArrayList<Task> custPending = new ArrayList<>();
    ArrayList<Task> custDone = new ArrayList<>();
    Button add,clear;
    ListView Pending,Done;
    Todapter pendingAdapter,doneAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        custDone.clear();
        custDone.addAll(getDoneFromDB());
        custPending.clear();
        custPending.addAll(getPendingFromDB());
        Intent parent = getIntent();
        setTitle(parent.getStringExtra("fName")+"'s Todo");
//        if(parent.hasExtra("pending"))
//        {
//            pending = parent.getStringArrayListExtra("pending");
//        }
//        if(parent.hasExtra("descpending")) descpending = parent.getStringArrayListExtra("descpending");
//        if(parent.hasExtra("done")) done = parent.getStringArrayListExtra("done");
//        if(parent.hasExtra("descdone")) descdone = parent.getStringArrayListExtra("descdone");
        add = findViewById(R.id.add);
        clear = findViewById(R.id.clear);
        Pending = findViewById(R.id.Pending);
        Done = findViewById(R.id.Done);

        pendingAdapter = new Todapter(this,custPending,this,this,this,this,this,this,false);
        doneAdapter = new Todapter(this,custDone,this,this,this,this,this,this,true);
        declared();
        Pending.setAdapter(pendingAdapter);
        Done.setAdapter(doneAdapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(allTasks.this,taskForm.class),1);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(allTasks.this);
                deleteDialog.setMessage("This will Delete all Done tasks!");
                deleteDialog.setCancelable(true);
                deleteDialog.setTitle("Are you sure?");

                deleteDialog.setNegativeButton("Keep them!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                deleteDialog.setPositiveButton("I am sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        done.clear();
                        descdone.clear();
                        custDone.clear();
                        doneAdapter.notifyDataSetChanged();


                    }
                });
                deleteDialog.show();


            }
        });


        save();



    }

    private ArrayList<Task> getPendingFromDB() {

        ArrayList<Task> tasks = new ArrayList<>();
        TodoOpenHandler openHandler = new TodoOpenHandler(this);
        SQLiteDatabase db = openHandler.getReadableDatabase();
        Cursor cursor = db.query(Constants.Tasks.TABLE_NAME,null,Constants.Tasks.DONE+"='True'",null,null,null,null);
        while(cursor.moveToNext())
        {
            Task task = new Task();
            task.taskName = cursor.getString(cursor.getColumnIndex(Constants.Tasks.TASK));
            task.Description = cursor.getString(cursor.getColumnIndex(Constants.Tasks.DESCRIPTION));
            task.Date = cursor.getString(cursor.getColumnIndex(Constants.Tasks.DATEDUE));
            tasks.add(task);
        }
        return tasks;

    }

    private ArrayList<Task> getDoneFromDB() {
        //TODO
        ArrayList<Task> tasks = new ArrayList<>();
        TodoOpenHandler openHandler = new TodoOpenHandler(this);
        SQLiteDatabase db = openHandler.getReadableDatabase();
        Cursor cursor = db.query(Constants.Tasks.TABLE_NAME,null,Constants.Tasks.DONE+" = 'False' ",null,null,null,null);
        while(cursor.moveToNext())
        {
            Task task = new Task();
            task.taskName = cursor.getString(cursor.getColumnIndex(Constants.Tasks.TASK));
            task.Description = cursor.getString(cursor.getColumnIndex(Constants.Tasks.DESCRIPTION));
            task.Date = cursor.getString(cursor.getColumnIndex(Constants.Tasks.DATEDUE));
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1)
        {
            if(resultCode==1)
            {
//                pending.add(data.getStringExtra("tName"));
//                descpending.add(data.getStringExtra("desc"));
                Task t = new Task();
                t.taskName = data.getStringExtra("tName");
                t.Description = data.getStringExtra("desc");
                custPending.add(t);
                pendingAdapter.notifyDataSetChanged();
//                save();
                TodoOpenHandler openHandler = new TodoOpenHandler(this);
                SQLiteDatabase db = openHandler.getWritableDatabase();
                ContentValues values = taskValues(t,Constants.False);
                db.insert(Constants.Tasks.TABLE_NAME,null,values);



            }
        }
        if(requestCode==2)
        {
            if(resultCode==1)
            {
//                pending.set(data.getIntExtra("pos",0),data.getStringExtra("tName"));
//                descpending.set(data.getIntExtra("pos",0),data.getStringExtra("desc"));
                Task t = new Task();
                t.taskName = data.getStringExtra("tName");
                t.Description = data.getStringExtra("desc");
                custPending.set(data.getIntExtra("pos",0),t);
                pendingAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ContentValues taskValues(Task t,String done) {
        Date dt = new Date(System.currentTimeMillis());
        ContentValues values = new ContentValues();
        values.put(Constants.Tasks.TASK,t.taskName);
        values.put(Constants.Tasks.DESCRIPTION,t.Description);
        values.put(Constants.Tasks.DONE,done);
        values.put(Constants.Tasks.DATEADDED, dt.getTime());
        values.put(Constants.Tasks.DATEDUE,t.Date);
        return values;

    }

    public void save()
    {
        Intent intent = new Intent();
        intent.putExtra("pending",pending);
        intent.putExtra("descpending",descpending);
        intent.putExtra("done",done);
        intent.putExtra("descdone",descdone);
        setResult(1,intent);
    }
    public void del(int a)
    {
//        done.remove(a);
//        descdone.remove(a);
        custDone.remove(a);
        doneAdapter.notifyDataSetChanged();
        save();
    }
    public void declared()
    {
        for(int i = 0;i<pending.size();i++)
        {
            Task t = new Task();
            t.taskName = pending.get(i);
            t.Description = descpending.get(i);
            custPending.add(t);
        }
        for(int i = 0;i<done.size();i++)
        {
            Task t = new Task();
            t.taskName = done.get(i);
            t.Description = descdone.get(i);
            custDone.add(t);
        }
        pendingAdapter.notifyDataSetChanged();
        doneAdapter.notifyDataSetChanged();
        save();
    }

    @Override
    public void onInfoClick(int position) {
        Intent intent = new Intent(allTasks.this,taskDetails.class);
        intent.putExtra("tNamedet",custPending.get(position).taskName);
        intent.putExtra("descdet",custPending.get(position).Description);
        intent.putExtra("pos",position);
        startActivityForResult(intent,2);
        save();
    }

    @Override
    public void onDoneClick(int position) {
//        descdone.add(descpending.get(position));
//        done.add(pending.get(position));

        Task t = new Task();
        t.taskName = custPending.get(position).taskName;
        t.Description = custPending.get(position).Description;
        custDone.add(t);
//        pending.remove(position);
//        descpending.remove(position);
        custPending.remove(position);
        doneAdapter.notifyDataSetChanged();
        pendingAdapter.notifyDataSetChanged();
//        save();

    }

    @Override
    public void onEditClick(int position, boolean done) {
        ArrayList<Task> curr = new ArrayList<>();

        if(done)
        {
             Toast.makeText(this,"Undelete to edit",Toast.LENGTH_SHORT).show();

        }
        else
        {
            curr = this.custPending;
            Intent intent = new Intent(allTasks.this,taskDetails.class);
            intent.putExtra("tNamedet",curr.get(position).taskName);
            intent.putExtra("descdet",curr.get(position).Description);
            intent.putExtra("pos",position);
            startActivityForResult(intent,2);
            save();

        }




    }

    @Override
    public void onRecurClick(int position, boolean done) {
        //TODO

    }

    @Override
    public void onDeleteClick(int position, boolean done) {
        final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(allTasks.this);
        final int p = position;
        deleteDialog.setMessage("Deleted tasks can't be recovered.");
        deleteDialog.setCancelable(true);
        deleteDialog.setTitle("Are you sure?");

        deleteDialog.setNegativeButton("Keep it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        deleteDialog.setPositiveButton("Remove it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                del(p);


            }
        });
        deleteDialog.show();


    }

    @Override
    public void onRestoreClick(int position) {


        Task t = new Task();
        t.taskName = (custDone.get(position).taskName);
        t.Description = custDone.get(position).Description;
        custPending.add(t);
        custDone.remove(position);

        doneAdapter.notifyDataSetChanged();
        pendingAdapter.notifyDataSetChanged();
        save();
    }
}
