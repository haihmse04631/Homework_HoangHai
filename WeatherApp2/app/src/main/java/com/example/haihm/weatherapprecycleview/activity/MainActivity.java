package com.example.haihm.weatherapprecycleview.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.haihm.weatherapprecycleview.R;
import com.example.haihm.weatherapprecycleview.adapter.WeatherTypeAdapter;
import com.example.haihm.weatherapprecycleview.mode.Weather;
import com.example.haihm.weatherapprecycleview.network.RetrofitInstance;
import com.example.haihm.weatherapprecycleview.network.WeatherInterface;
import com.example.haihm.weatherapprecycleview.network.WeatherResponseJSON;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_list_weather)
    RecyclerView recyclerView;
    @BindView(R.id.edt_city)
    EditText edtCity;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    Context context;
    private WeatherTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ButterKnife.bind(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtCity.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter City Name!", Toast.LENGTH_LONG).show();
                }else{
                    loadData();
                }
            }
        });





    }

    private void loadData() {
        final WeatherInterface weatherInterface = RetrofitInstance.getInstance().create(WeatherInterface.class);
        weatherInterface.getWeather(edtCity.getText().toString().trim(), "0853d0be75f3a8b3427adbf35196882a").enqueue(new Callback<WeatherResponseJSON>() {
            @Override
            public void onResponse(Call<WeatherResponseJSON> call, Response<WeatherResponseJSON> response) {
                Log.e("status" , response.isSuccessful() + " ");
                if(response.isSuccessful()){
                    int day = 1;
                    List<WeatherResponseJSON.SubObjectJSON> list;
                    List<Weather> weatherList = new ArrayList<>();
                    list = response.body().list;
                    for(WeatherResponseJSON.SubObjectJSON itemList: list){
                        String tempDay = "Day: " + day;
                        day++;
                        String main = itemList.weather.get(0).main;
                        String des = itemList.weather.get(0).description;
                        float per = itemList.pressure;
                       // Log.e("check" , weatherObj.toString() + day);
                        weatherList.add(new Weather(per,des, main, tempDay));
                    }

                    for(int i=0; i<weatherList.size(); i++){
                        Log.e("check 2", weatherList.get(i).toString());
                    }

                    adapter = new WeatherTypeAdapter(weatherList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                   // adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "City name is incorrect!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponseJSON> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Internet!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
