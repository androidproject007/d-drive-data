package com.schoolmanagement.Teachers.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.schoolmanagement.Teachers.Adapter.chapterAllMaterialAdapter;


public class AllMaterialFragment extends Fragment {

    RecyclerView teachers_all_matrial;
    RecyclerView.LayoutManager layoutManager;
    AddHomeworkAdapter addHomeworkAdapter;
    RecyclerView chapter_all_matrial;
    RecyclerView.LayoutManager layoutManager1;
    chapterAllMaterialAdapter chapterAllMaterialAdaptera;
    FrameLayout add;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;

    public AllMaterialFragment(Activity activity, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_activity_all_material, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(),drawer, header, "Material");

        teachers_all_matrial=(RecyclerView)rootView.findViewById(R.id.teachers_all_matrial);
        layoutManager= new LinearLayoutManager(activity);
        teachers_all_matrial.setLayoutManager(layoutManager);

        addHomeworkAdapter= new AddHomeworkAdapter(activity);
        teachers_all_matrial.setAdapter(addHomeworkAdapter);

        chapter_all_matrial=(RecyclerView)rootView.findViewById(R.id.chapter_all_matrial);

        layoutManager1= new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false);
        chapter_all_matrial.setLayoutManager(layoutManager1);

        chapterAllMaterialAdaptera= new chapterAllMaterialAdapter(activity);
        chapter_all_matrial.setAdapter(chapterAllMaterialAdaptera);

        add=(FrameLayout)rootView.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                pushFragment(new AddMaterialFragment(activity, fragmentManager, header, drawer), "Education", true);
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
