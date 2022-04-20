package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.OnlineStudyAdapter;
import com.schoolmanagement.Student.Model.AttandenceList;

import java.util.ArrayList;
import java.util.List;

public class OnlineStudyFragment extends Fragment {

    ImageView back,home;
     RecyclerView rv_noticeboard;
     RecyclerView.LayoutManager layoutManager;
     OnlineStudyAdapter noticeAdapter;
     TextView header_name;
     Activity activity;
     public FragmentManager fragmentManager;
     RelativeLayout header;
     DrawerLayout drawer;

    public OnlineStudyFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.student_fragment_daily_homework, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Online Study");

        rv_noticeboard=(RecyclerView)rootView.findViewById(R.id.rvlist_student_homework);
        layoutManager= new LinearLayoutManager(activity);
        rv_noticeboard.setLayoutManager(layoutManager);
        noticeAdapter= new OnlineStudyAdapter(activity,fragmentManager,drawer,header);
        rv_noticeboard.setAdapter(noticeAdapter);

        return  rootView;
    }
}
