package com.example.haihoang.freemusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.adapter.DownloadListAdapter;
import com.example.haihoang.freemusic.database.OfflineListManager;
import com.example.haihoang.freemusic.database.TopSongModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    @BindView(R.id.rv_list_download)
    RecyclerView rvListDownload;
    private DownloadListAdapter downloadListAdapter;
    private List<TopSongModel> downloadList = new ArrayList<>();
    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        setupUI(view);
        loadData();
        return view;
    }

    private void loadData() {
        if(OfflineListManager.listSongName != null){
            for(int i=0 ; i<OfflineListManager.listSongName.size(); i++){
                String s = OfflineListManager.listSongName.get(i);
                String tempList[] = s.split("-");

                TopSongModel topSongModel = new TopSongModel();
                topSongModel.song = tempList[0];
                topSongModel.singer = tempList[1];
                topSongModel.url = getActivity().getExternalFilesDir("").getPath() + "/" + s;
                topSongModel.lagreImage = "R.drawable.offline_song";
                topSongModel.smallImage = "R.drawable.offline_song";
                topSongModel.status = 1;
                downloadList.add(topSongModel);
                downloadListAdapter.notifyItemChanged(i);
            }
        }

    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        downloadListAdapter = new DownloadListAdapter(getContext(), downloadList);
        rvListDownload.setAdapter(downloadListAdapter);
        rvListDownload.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
