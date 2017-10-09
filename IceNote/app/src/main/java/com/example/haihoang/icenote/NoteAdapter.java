package com.example.haihoang.icenote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by haihoang on 10/9/17.
 */

public class NoteAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<Note> noteList;

    public NoteAdapter(Context context, int layout, List<Note> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }


    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTitle;
        TextView txtContent;
        TextView txtTime;
        View status;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtTitle = view.findViewById(R.id.txtTitle);
            viewHolder.txtContent = view.findViewById(R.id.txtContent);
            viewHolder.txtTime = view.findViewById(R.id.txtTime);
            viewHolder.status = view.findViewById(R.id.viewBookmark);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Note note = noteList.get(i);

        viewHolder.txtTitle.setText(note.getTitle());
        viewHolder.txtContent.setText(note.getContent());
        viewHolder.txtTime.setText(note.getTime());
        if(note.getBookmark()){
            viewHolder.status.setBackgroundResource(R.color.done);
        }else{
            viewHolder.status.setBackgroundResource(R.color.not_done);
        }

        return view;
    }
}
