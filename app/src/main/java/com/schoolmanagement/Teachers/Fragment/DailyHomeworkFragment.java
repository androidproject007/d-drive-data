package com.schoolmanagement.Teachers.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Teachers.Adapter.AddHomeworkAdapter;

public class DailyHomeworkFragment extends Fragment {

    ImageView back,home;
    FrameLayout add_home_work;
    RecyclerView teacher_home_work_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    AddHomeworkAdapter addHomeworkAdapter;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;

    public DailyHomeworkFragment(Activity activity, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_daily_home_workactivity, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "DailyHomework");

        back=(ImageView)rootView.findViewById(R.id.back);
        home=(ImageView)rootView.findViewById(R.id.home);
        add_home_work=(FrameLayout)rootView.findViewById(R.id.add_home_work);
        teacher_home_work_recyclerview=(RecyclerView)rootView.findViewById(R.id.teacher_home_work_recyclerview);
        layoutManager= new LinearLayoutManager(activity);
        teacher_home_work_recyclerview.setLayoutManager(layoutManager);
        addHomeworkAdapter= new AddHomeworkAdapter(activity);
        teacher_home_work_recyclerview.setAdapter(addHomeworkAdapter);

        add_home_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                pushFragment(new AddDailyHomeworkFragment(activity, fragmentManager, header, drawer), "Education", true);
//                Intent i= new Intent(DailyHomeworkFragment.this,AddDailyHomeworkActivity.class);
//                startActivity(i);
//                finish();
            }
        });

        return  rootView;
    }


    public void pushFragment(Fragment frag, String tag, Boolean addtobackstack) {
//            FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.rl_content, frag, tag);
        if (addtobackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
