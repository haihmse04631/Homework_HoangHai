package com.example.haihoang.icenote;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class IceNote extends AppCompatActivity{

    private List<Note> noteList = new ArrayList<>();
    private ListView lvListNote;
    private FloatingActionButton btnAdd;
    NoteAdapter noteAdapter;
    int position;
    //public static final String NOTE_KEY = "note_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_note);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar_main);

        noteList = DatabaseHandle.getInstance(this).getListNote();

        lvListNote = (ListView) findViewById(R.id.lvListNote);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);

        noteAdapter = new NoteAdapter(this, R.layout.item_list_note, noteList);
        lvListNote.setAdapter(noteAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IceNote.this, AddNote.class);
                startActivity(intent);
            }
        });

        lvListNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(IceNote.this, ShowOwnNote.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                bundle.putString("id", noteList.get(i).getId());
                bundle.putString("title", noteList.get(i).getTitle());
                bundle.putString("content", noteList.get(i).getContent());
                bundle.putBoolean("status", false);
                intent.putExtra("myNote", bundle);
                startActivity(intent);
            }
        });

        lvListNote.setOnItemLongClickListener(new DialogSelect());

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("check", "onresume");
        noteAdapter.notifyDataSetChanged();
    }


    private class DialogSelect implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
            position = i;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(IceNote.this);
            alertDialogBuilder.setMessage(noteList.get(i).getTitle())
            .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    noteList.get(position).setBookmark(true);
                    DatabaseHandle.getInstance(IceNote.this).updateBookmark(noteList.get(position).getId());
                    noteAdapter.notifyDataSetChanged();
                }
            })
            .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DatabaseHandle.getInstance(IceNote.this).deleteNote(noteList.get(position).getId());
                    noteList.remove(noteList.get(position));
                    noteAdapter.notifyDataSetChanged();
                }
            })
            .setNeutralButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(IceNote.this, ShowOwnNote.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    bundle.putString("id", noteList.get(position).getId());
                    bundle.putString("title", noteList.get(position).getTitle());
                    bundle.putString("content", noteList.get(position).getContent());
                    bundle.putBoolean("status", true);
                    intent.putExtra("myNote", bundle);
                    startActivity(intent);
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }

}
