package com.example.haihoang.phonecall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by haihoang on 10/5/17.
 */

public class ContactsTab extends Fragment {
    ListView lvContacts;
    ArrayList<Contact> arrContacts;
    ContactAdapter adapter;
    View rootView;
    Intent callIntent;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.contacts, container, false);

        setupUI();

        adapter = new ContactAdapter(getActivity(), R.layout.contact_element, arrContacts);
        lvContacts.setAdapter(adapter);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + arrContacts.get(i).getPhoneNumber()));

                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                }else{
                    try {
                        startActivity(callIntent);
                    }catch (SecurityException e){
                        e.printStackTrace();
                    }
                }

            }

        });

        return rootView;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startActivity(callIntent);
                }else{
                    Toast.makeText(getActivity(),"Cancel Call!", Toast.LENGTH_LONG);
                }
                return;
        }
    }

    private void setupUI() {
        lvContacts = (ListView) rootView.findViewById(R.id.lvContacts);
        arrContacts = new ArrayList<>();
        arrContacts.add(new Contact("Hoang Hai", "0986913011", R.drawable.avatar));
        arrContacts.add(new Contact("Nguyen Van A", "0999999999", R.drawable.avatar));
        arrContacts.add(new Contact("Hoang Hung", "0977995113", R.drawable.avatar));
        arrContacts.add(new Contact("Nguyen Van B", "0122345664", R.drawable.avatar));
        arrContacts.add(new Contact("Hoang Phong", "0977588359", R.drawable.avatar));
        arrContacts.add(new Contact("Hoang Hai", "0986913011", R.drawable.avatar));
        arrContacts.add(new Contact("Hoang Tuan", "0977995113", R.drawable.avatar));
        arrContacts.add(new Contact("Hoang Hai", "0986913011", R.drawable.avatar));
        arrContacts.add(new Contact("Nguyen Van C", "0988888888", R.drawable.avatar));


    }
}
