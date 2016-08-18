package com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by NehaRege on 8/17/16.
 */
public class FirstFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private String title;
    private int page;

    public static FirstFragment newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FirstFragment fragmentFirst = new FirstFragment();
//        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt(ARG_PAGE);
//      title = getArguments().getString("someTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_first, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.txtv);
        tvLabel.setText("Fragment "+page);
        return view;

    }

}
