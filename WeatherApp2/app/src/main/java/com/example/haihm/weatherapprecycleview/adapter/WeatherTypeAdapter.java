package com.example.haihm.weatherapprecycleview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haihm.weatherapprecycleview.R;
import com.example.haihm.weatherapprecycleview.mode.Weather;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haihm on 11/20/2017.
 */

public class WeatherTypeAdapter extends RecyclerView.Adapter<WeatherTypeAdapter.WeatherTypeViewHodel> {
     List<Weather> weatherList;

     public WeatherTypeAdapter(List<Weather> weatherList) {
          this.weatherList = weatherList;
     }

     @Override
     public WeatherTypeViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
          LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
          View view = layoutInflater.inflate(R.layout.item_weather_list, parent, false);
          return new WeatherTypeViewHodel(view);
     }

     @Override
     public void onBindViewHolder(WeatherTypeViewHodel holder, int position) {
          holder.setData(weatherList.get(position));

     }

     @Override
     public int getItemCount() {
          return weatherList.size();
     }

     public class WeatherTypeViewHodel extends RecyclerView.ViewHolder{
         @BindView(R.id.iv_weather)
          ImageView imgWeather;
         @BindView(R.id.tv_day)
          TextView tvDay;
         @BindView(R.id.tv_perssure)
          TextView tvPerssure;
         @BindView(R.id.tv_main)
          TextView tvMain;
         @BindView(R.id.tv_description)
          TextView tvDes;

          public WeatherTypeViewHodel(View itemView) {
               super(itemView);
               ButterKnife.bind(this, itemView);
          }

          public void setData(Weather weather){
               imgWeather.setImageResource(weather.imgID);
               tvDay.setText(weather.day + "");
               tvMain.setText(weather.main);
               tvPerssure.setText(weather.perssure + "");
               tvDes.setText(weather.description);
          }
     }

}
