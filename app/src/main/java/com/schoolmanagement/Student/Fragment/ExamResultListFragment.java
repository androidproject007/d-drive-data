package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.ExamListAdapter;


public class ExamResultListFragment extends Fragment {

    ImageView back,home;
    RecyclerView teacher_exam_result_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    ExamListAdapter adapter;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;

    public ExamResultListFragment(Activity activity, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_activity_exam_result, container, false);

        CustomHeaderWithRelative.setOuter(getActivity(),drawer, header, "Exam Result");
        back=(ImageView)rootView.findViewById(R.id.back);
        home=(ImageView)rootView.findViewById(R.id.home);
        teacher_exam_result_recyclerview=(RecyclerView)rootView.findViewById(R.id.teacher_exam_result_recyclerview);
        layoutManager= new LinearLayoutManager(activity);
        teacher_exam_result_recyclerview.setLayoutManager(layoutManager);
        adapter= new ExamListAdapter(activity,fragmentManager,drawer,header);
        teacher_exam_result_recyclerview.setAdapter(adapter);
       return rootView;
    }
}
