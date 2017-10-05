package com.example.haihoang.homeword_gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvListCountryFlags;
    ArrayList<CountryFlag> arrCountryFlags;
    CountryFlagAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetupUI();

        adapter = new CountryFlagAdapter(this, R.layout.image_element, arrCountryFlags);
        gvListCountryFlags.setAdapter(adapter);


    }

    private void SetupUI() {
        gvListCountryFlags = (GridView) findViewById(R.id.gvCountryFlags);
        arrCountryFlags = new ArrayList<>();
        arrCountryFlags.add(new CountryFlag("Australia", R.drawable.australia));
        arrCountryFlags.add(new CountryFlag("Argentina", R.drawable.argentina));
        arrCountryFlags.add(new CountryFlag("Benin", R.drawable.benin));
        arrCountryFlags.add(new CountryFlag("Brazin", R.drawable.brazin));
        arrCountryFlags.add(new CountryFlag("Canada", R.drawable.canada));
        arrCountryFlags.add(new CountryFlag("Cape Verder", R.drawable.cape_verder));
        arrCountryFlags.add(new CountryFlag("China", R.drawable.china));
        arrCountryFlags.add(new CountryFlag("Greece", R.drawable.greece));
        arrCountryFlags.add(new CountryFlag("Indian", R.drawable.indian));
        arrCountryFlags.add(new CountryFlag("Japan", R.drawable.japan));
        arrCountryFlags.add(new CountryFlag("Kenya", R.drawable.kenya));
        arrCountryFlags.add(new CountryFlag("Mexico", R.drawable.mexico));
        arrCountryFlags.add(new CountryFlag("Nepa", R.drawable.nepa));
        arrCountryFlags.add(new CountryFlag("U.k", R.drawable.uk));
        arrCountryFlags.add(new CountryFlag("Viet Nam", R.drawable.viet_nam));

    }
}
