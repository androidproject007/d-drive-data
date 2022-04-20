package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;



public class ExamFragment extends Fragment {
    ImageView back,home;
    CardView id_exam,id_report,id_result,id_self_study;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public ExamFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
   
        View   rootView = inflater.inflate(R.layout.student_fargment_exam, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Online Exam");

        id_self_study=(CardView)rootView.findViewById(R.id.id_self_study);
        id_self_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i= new Intent(activity, ExamSubjectctivity.class);
//               activity. startActivity(i);
                pushFragment(new ExamSubjectFragment(activity, fragmentManager, header, drawer), "Education", true);

            }
        });

        id_exam=(CardView)rootView.findViewById(R.id.id_exam);
        id_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new ExamSubjectFragment(activity, fragmentManager, header, drawer), "Education", true);
            }
        });

//        id_report=(CardView)rootView.findViewById(R.id.id_report);
//        id_report.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent i= new Intent(activity, Report_Graph_Activity.class);
////                activity.startActivity(i);
//            }
//        });

        id_result=(CardView)rootView.findViewById(R.id.id_result);
        id_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new ExamResultFragment(activity, fragmentManager, header, drawer, "1"), "Education", true);
//                Intent i= new Intent(activity, ExamResultctivity.class);
//                activity.startActivity(i);
            }
        });

        return rootView;

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
