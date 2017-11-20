package com.example.haihoang.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edtCity;
    Button btnCheck;
    TextView tvCity, tvStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCity = findViewById(R.id.edtCity);
        btnCheck = findViewById(R.id.btnCheck);
        tvCity = findViewById(R.id.txtCity);
        tvStatus = findViewById(R.id.txtStatus);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtCity.getText().toString().trim().equals("")){
                    WeatherInterface weatherInterface = RetrofitInstance.getInstance().create(WeatherInterface.class);
                    weatherInterface.getWeather(edtCity.getText().toString().trim(), "c49f2a5b07ce03250befb407c4410be3").enqueue(new Callback<WeatherTypeResponseJSON>() {
                        @Override
                        public void onResponse(retrofit2.Call<WeatherTypeResponseJSON> call, Response<WeatherTypeResponseJSON> response) {
                            Log.e("Status", response.isSuccessful() + "");
                            if(response.isSuccessful()){
                                List<WeatherTypeResponseJSON.SubWeatherObj> list = response.body().weather;

                                for(int i=0; i<list.size(); i++){
                                   tvStatus.setText("id: " + list.get(i).id + "\n" +
                                           "main: " + list.get(i).main + "\n" +
                                            "description: " + list.get(i).description);
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "Please enter correct city!", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(retrofit2.Call<WeatherTypeResponseJSON> call, Throwable t) {
                            Log.e("Status", "failer");
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "Please enter city!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}


