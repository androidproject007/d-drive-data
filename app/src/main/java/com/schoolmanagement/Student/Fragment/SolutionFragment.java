package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.SoultionAppAdapter;
import com.schoolmanagement.Student.Model.Self_Study_Gks_Object;

import java.util.ArrayList;

public class SolutionFragment extends Fragment {

    SoultionAppAdapter adapter;
    RecyclerView solution_exam;
    ArrayList<Self_Study_Gks_Object> items = new ArrayList<>();
    Self_Study_Gks_Object item;
    RecyclerView.LayoutManager layoutManager;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public SolutionFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.student_fragment_daily_homework, container, false);
        CustomHeaderWithRelative.setInnerToolbar(getActivity(),  header, "Exam Solution");
        solution_exam=(RecyclerView)rootView.findViewById(R.id.rvlist_student_homework);
        layoutManager= new LinearLayoutManager(activity);
        solution_exam.setLayoutManager(layoutManager);
        item = new Self_Study_Gks_Object("1",
                "what is Android",
                "",
                "Operating System",
                "Test",
                "about",
                "rest",
                "Not Attempt",
                "Operating System",
                "",
                false
        );

        items.add(item);
        item = new Self_Study_Gks_Object("2",
                "what is Object",
                "",
                "Operating System",
                "Entity",
                "about",
                "rest",
                "Not Attempt",
                "Entity",
                "",
                false
        );

//        items.add(item);
//        item = new Self_Study_Gks_Object("3",
//                "what is Interface",
//                "",
//                "Operating System",
//                "Entity",
//                "Internal Communication",
//                "rest",
//                "Not Attempt",
//                "Internal Communication",
//                "",
//                false
//        );
//
//        items.add(item);

        adapter= new SoultionAppAdapter(activity,items);
        solution_exam.setAdapter(adapter);
        return  rootView;
    }
}
