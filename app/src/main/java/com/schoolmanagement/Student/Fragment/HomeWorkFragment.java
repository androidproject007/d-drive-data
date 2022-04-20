package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.HomeworkAdapter;
import com.schoolmanagement.Student.Model.AttandenceList;
import java.util.ArrayList;
import java.util.List;

public class HomeWorkFragment extends Fragment {
    ImageView back,home;
    RecyclerView rvlist_student_homework;
    RecyclerView.LayoutManager layoutManager;
    HomeworkAdapter adapter;
    List<AttandenceList> attandenceLists= new ArrayList<>();
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    String value;

    public HomeWorkFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer, String value) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
        this.value=value;
      }

       @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.student_fragment_daily_homework, container, false);

        if(value=="1"){
            CustomHeaderWithRelative.setInnerToolbar(getActivity(), header, "Daily Homework");
        }else {
            CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Daily Homework");
        }
        rvlist_student_homework=(RecyclerView)rootView.findViewById(R.id.rvlist_student_homework);
        layoutManager= new LinearLayoutManager(activity);
        rvlist_student_homework.setLayoutManager(layoutManager);

        attandenceLists= new ArrayList<>();
        attandenceLists.add(new AttandenceList("1","1/3/2021","0"));
        attandenceLists.add(new AttandenceList("1","2/3/2021","1"));
        attandenceLists.add(new AttandenceList("1","3/3/2021","0"));
        attandenceLists.add(new AttandenceList("1","4/3/2021","0"));
        attandenceLists.add(new AttandenceList("1","5/3/2021","1"));
        attandenceLists.add(new AttandenceList("1","5/3/2021","0"));
        attandenceLists.add(new AttandenceList("1","10/3/2021","1"));
        attandenceLists.add(new AttandenceList("1","12/3/2021","0"));
        attandenceLists.add(new AttandenceList("1","14/4/2021","1"));
        attandenceLists.add(new AttandenceList("1","15/4/2021","0"));
        attandenceLists.add(new AttandenceList("1","18/4/2021","1"));
        attandenceLists.add(new AttandenceList("1","18/4/2021","0"));
        adapter= new HomeworkAdapter(activity,attandenceLists);
        rvlist_student_homework.setAdapter(adapter);
        return rootView;
    }
}
