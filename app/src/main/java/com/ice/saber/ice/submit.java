package com.ice.saber.ice;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class submit extends Fragment {

private  View mview;
    private ListView list;
    private TextView text;
    private ArrayList<String> result;
    public submit() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mview= inflater.inflate(R.layout.fragment_submit, container, false);
        list=(ListView)mview.findViewById(R.id.sub_list);
        text=(TextView) mview.findViewById(R.id.sub_text);
        return mview;
    }

}
