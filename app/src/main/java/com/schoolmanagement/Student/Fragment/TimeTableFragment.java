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
import com.schoolmanagement.Student.Adapter.TimetableDateAdapter;
import com.schoolmanagement.Student.Adapter.TimetableDetailsAdapter;
import com.schoolmanagement.Student.Model.AttandenceList;
import com.schoolmanagement.Student.Model.DayList;

import java.util.ArrayList;
import java.util.List;

public class TimeTableFragment extends Fragment {

    ImageView back,home;
    RecyclerView date_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    TimetableDateAdapter timetableDateAdapter;
    RecyclerView time_table_recyclerview;
    RecyclerView.LayoutManager layoutManager1;
    TimetableDetailsAdapter timetableDetailsAdapter;
    List<AttandenceList> attandenceLists= new ArrayList<>();
    List<DayList> dayList= new ArrayList<>();
    TextView header_name;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public TimeTableFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
            this.activity = context;
            this.fragmentManager = fragmentManager;
            this.header = lin_header;
            this.drawer = drawer;
    }

     @Override
     public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
     View   rootView = inflater.inflate(R.layout.student_activity_timetable, container, false);
     CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Time Table");

        dayList= new ArrayList<>();
        dayList.add(new DayList("1","01/2/2021","Mon"));
        dayList.add(new DayList("2","02/2/2021","Tue"));
        dayList.add(new DayList("3","03/2/2021","Wed"));
        dayList.add(new DayList("4","04/2/2021","Thu"));
        dayList.add(new DayList("5","05/2/2021","Fri"));
        dayList.add(new DayList("6","06/2/2021","Sat"));
        dayList.add(new DayList("7","07/2/2021","Sun"));

        date_recyclerview=(RecyclerView)rootView.findViewById(R.id.date_recyclerview);
        layoutManager= new LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false);
        date_recyclerview.setLayoutManager(layoutManager);

        timetableDateAdapter= new TimetableDateAdapter(activity,dayList);
        date_recyclerview.setAdapter(timetableDateAdapter);

//        date_recyclerview.addOnItemTouchListener(
//                new RecyclerItemClickListener(
//                        getContext(),
//                        recyclerViewObject,
//                        new RecyclerItemClickListener.OnItemClickListener() {
//                            @Override public void onItemClick(View view, int position) {
//                                // view is the clicked view (the one you wanted
//                                // position is its position in the adapter
//                            }
//                            @Override public void onLongItemClick(View view, int position) {
//                            }
//                        }
//                )
//        );

        time_table_recyclerview=(RecyclerView)rootView.findViewById(R.id.time_table_recyclerview);
        layoutManager1= new LinearLayoutManager(activity);
        time_table_recyclerview.setLayoutManager(layoutManager1);
        timetableDetailsAdapter= new TimetableDetailsAdapter(activity);
        time_table_recyclerview.setAdapter(timetableDetailsAdapter);
        return  rootView;
    }
}
