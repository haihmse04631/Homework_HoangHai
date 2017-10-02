package com.example.haihoang.homeword_gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by haihoang on 10/2/17.
 */

public class CountryFlagAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CountryFlag> countryFlagList;

    public CountryFlagAdapter(Context context, int layout, List<CountryFlag> countryFlagList) {
        this.context = context;
        this.layout = layout;
        this.countryFlagList = countryFlagList;
    }

    @Override
    public int getCount() {
        return countryFlagList.size();
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
        ImageView imgCountryFlag;
        TextView countryName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.imgCountryFlag = view.findViewById(R.id.imgCountry);
            viewHolder.countryName = view.findViewById(R.id.txtCountry);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        CountryFlag countryFlag = countryFlagList.get(i);

        viewHolder.imgCountryFlag.setImageResource(countryFlag.getImgCountry());
        viewHolder.countryName.setText(countryFlag.getCountryName());

        return view;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
