package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.ExamAdapter;
import com.schoolmanagement.Student.HomeActivity;
import com.schoolmanagement.Student.Model.AttandenceList;

import java.util.ArrayList;
import java.util.List;

public class ExamSubjectFragment extends Fragment {

    ImageView back,home;
    RecyclerView subject_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    ExamAdapter examAdapter;
    List<AttandenceList> attandenceLists= new ArrayList<>();
    TextView header_name;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public ExamSubjectFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.student_fragment_daily_homework, container, false);
        CustomHeaderWithRelative.setInnerToolbar(getActivity(),  header, "Select Subject");
        subject_recyclerview=(RecyclerView)rootView.findViewById(R.id.rvlist_student_homework);
        layoutManager= new GridLayoutManager(activity,2);
        subject_recyclerview.setLayoutManager(layoutManager);
        examAdapter= new ExamAdapter(activity,fragmentManager,drawer,header);
        subject_recyclerview.setAdapter(examAdapter);
        return  rootView;
    }
}
