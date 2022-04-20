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
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.FeesAdapter;
import com.schoolmanagement.Student.Model.AttandenceList;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

     public Activity activity;
     public FragmentManager fragmentManager;
     RelativeLayout header;
     DrawerLayout drawer;

    public ProfileFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.student_activity_profile, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Profile");
        return rootView;
    }
}
