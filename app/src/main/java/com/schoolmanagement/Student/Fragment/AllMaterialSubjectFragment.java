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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.AllSubjectAdapter;
import com.schoolmanagement.Student.Model.AttandenceList;

import java.util.ArrayList;
import java.util.List;

public class AllMaterialSubjectFragment extends Fragment {
    RecyclerView subject_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    AllSubjectAdapter examAdapter;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public AllMaterialSubjectFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.student_fragment_daily_homework, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "All Subject");
        subject_recyclerview=(RecyclerView)rootView.findViewById(R.id.rvlist_student_homework);
        layoutManager= new GridLayoutManager(activity,2);
        subject_recyclerview.setLayoutManager(layoutManager);
        examAdapter= new AllSubjectAdapter(activity,fragmentManager,header);
        subject_recyclerview.setAdapter(examAdapter);

       return rootView;
    }
}
