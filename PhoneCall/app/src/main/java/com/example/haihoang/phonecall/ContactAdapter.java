package com.example.haihoang.phonecall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by haihoang on 10/5/17.
 */

public class ContactAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<Contact> contactsList;

    public ContactAdapter(Context context, int layout, List<Contact> contactsList) {
        this.context = context;
        this.layout = layout;
        this.contactsList = contactsList;
    }


    @Override
    public int getCount() {
        return contactsList.size();
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
        ImageView imgAvatar;
        TextView txtName;
        TextView txtPhoneNumber;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.imgAvatar = view.findViewById(R.id.imgAvatar);
            viewHolder.txtName = view.findViewById(R.id.txtName);
            viewHolder.txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Contact contact = contactsList.get(i);

        viewHolder.imgAvatar.setImageResource(contact.getAvatar());
        viewHolder.txtName.setText(contact.getName());
        viewHolder.txtPhoneNumber.setText(contact.getPhoneNumber());

        return view;
    }
}
