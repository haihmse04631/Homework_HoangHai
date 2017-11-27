package com.example.haihoang.freemusic.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.adapter.MusicTypeAdapter;
import com.example.haihoang.freemusic.database.MusicTypeModel;
import com.example.haihoang.freemusic.network.MusicInterface;
import com.example.haihoang.freemusic.network.MusicTypeResponseJSON;
import com.example.haihoang.freemusic.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicTypeFragment extends Fragment {
    @BindView(R.id.rv_list_type_music)
    RecyclerView recyclerView;
    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    Context context;
    private MusicTypeAdapter adapter;
    public MusicTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_type, container, false);
        ButterKnife.bind(this, view);
        context = getContext();

        adapter = new MusicTypeAdapter(musicTypeModelList, context);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(context));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                context,
                2
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return (position % 3 == 0 ? 2 : 1);
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);

        loadData();


        // Inflate the layout for this fragment
        return view;
    }

    private void loadData() {
        MusicInterface musicTypeInterface =
                RetrofitInstance.getInstance().create(MusicInterface.class);

        musicTypeInterface.getMusicType().enqueue(new Callback<MusicTypeResponseJSON>() {
            @Override
            public void onResponse(Call<MusicTypeResponseJSON> call, Response<MusicTypeResponseJSON> response) {
                List<MusicTypeResponseJSON.SubObjectJSON> list = response.body().subgenres;

                for(int i=0; i<list.size() - 1; i++){
                    MusicTypeModel musicTypeModel = new MusicTypeModel();
                    musicTypeModel.id = list.get(i).id;
                    musicTypeModel.key = list.get(i).translation_key;
                    musicTypeModel.imageID = getContext().getResources().getIdentifier(
                            "genre_x2_" + musicTypeModel.id,
                            "raw",
                            getContext().getPackageName());
                    musicTypeModelList.add(musicTypeModel);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MusicTypeResponseJSON> call, Throwable t) {

            }
        });
    }

}
