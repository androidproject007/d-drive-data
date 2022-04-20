package com.schoolmanagement.Parents.Fragment;

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


public class ProfileParentsFragment extends Fragment {

    ImageView back,home;
    RecyclerView rv_noticeboard;
    RecyclerView.LayoutManager layoutManager;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    TextView person_name,mobile_number,address,designation,email;

    public ProfileParentsFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.parents_fragment_profile, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Profile");

        person_name=(TextView)rootView.findViewById(R.id.person_name);
        mobile_number=(TextView)rootView.findViewById(R.id.mobile_number);
        address=(TextView)rootView.findViewById(R.id.address);
        designation=(TextView)rootView.findViewById(R.id.designation);
        email=(TextView)rootView.findViewById(R.id.email);

        person_name.setText("Person Name");
        mobile_number.setText("917418529630");
        address.setText("103, Ratnamani Complex Ahmedabad");
        designation.setText("BCA");
        email.setText("abc@gmail.com");
    return rootView;
    }
}
