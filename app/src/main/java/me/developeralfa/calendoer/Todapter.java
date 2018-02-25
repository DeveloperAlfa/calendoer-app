package me.developeralfa.calendoer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devalfa on 12/2/18.
 */

public class Todapter extends BaseAdapter {

    ArrayList<Task> tasks;
    Context context;
    infoClickListener info;
    restoreClickListener restoreClickListener;
    doneClickListener doneClickListener;
    recurClickListener recurClickListener;
    deleteClickListener deleteClickListener;
    editClickListener editClickListener;

    int im = R.mipmap.bulb_lit;
    boolean done;
    public Todapter(Context context, ArrayList<Task> tasks,infoClickListener infoClickListener,restoreClickListener restoreClickListener,recurClickListener recurClickListener,editClickListener editClickListener,doneClickListener doneClickListener,deleteClickListener deleteClickListener,boolean done)
    {
        this.tasks = tasks;
        this.context = context;
        this.restoreClickListener = restoreClickListener;
        this.recurClickListener = recurClickListener;
        this.deleteClickListener = deleteClickListener;
        this.editClickListener = editClickListener;
        this.doneClickListener = doneClickListener;
        this.info = infoClickListener;
        this.done = done;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task  getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(done)
        {
             v = inflater.inflate(R.layout.done,parent,false);
        }
        else v = inflater.inflate(R.layout.todo_item,parent,false);
        TextView textView =  v.findViewById(R.id.textset);
        ImageButton one;
        final int p = position;



        final ImageButton recur = v.findViewById(R.id.recur);
        ImageButton edit = v.findViewById(R.id.editit);
        ImageButton delete= v.findViewById(R.id.deleteit);
        textView.setText(tasks.get(position).taskName);
        recur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recurClickListener.onRecurClick(p,done);
                recur.setImageResource(im);
                if(im==R.mipmap.bulb_lit) im = R.mipmap.bulb_off;
                else im = R.mipmap.bulb_lit;

            }
        });
        if(!done)
        {
            one = v.findViewById(R.id.info);
            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    info.onInfoClick(p);

                }

            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    doneClickListener.onDoneClick(p);
                }
            });
        }
        else {
            one = v.findViewById(R.id.restore);
            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restoreClickListener.onRestoreClick(p);

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    deleteClickListener.onDeleteClick(p,done);
                }
            });
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editClickListener.onEditClick(p,done);
            }
        });
        return v;
    }
}
