package com.example.haihoang.freemusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.adapter.DownloadListAdapter;
import com.example.haihoang.freemusic.database.OfflineListManager;
import com.example.haihoang.freemusic.database.OfflineSongModel;
import com.example.haihoang.freemusic.database.TopSongModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    @BindView(R.id.rv_list_download)
    RecyclerView rvListDownload;
    private DownloadListAdapter downloadListAdapter;
    private List<OfflineSongModel> downloadList = new ArrayList<>();
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

                OfflineSongModel offlineSongModel = new OfflineSongModel();
                offlineSongModel.song = tempList[0];
                offlineSongModel.singer = tempList[1];
                offlineSongModel.path = getActivity().getExternalFilesDir("").getPath() + "/" + s;
               // Log.e("loadData", offlineSongModel.path);
                downloadList.add(offlineSongModel);
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
