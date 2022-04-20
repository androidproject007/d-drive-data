package com.schoolmanagement.Transport.Fragment;

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


import com.schoolmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileTransportFragment extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public ProfileTransportFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    public ProfileTransportFragment(Activity homeTransportActivity, FragmentManager supportFragmentManager) {
        this.activity = homeTransportActivity;
        this.fragmentManager = supportFragmentManager;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.parents_fragment_profile, container, false);

      TextView person_name = (TextView)rootView.findViewById(R.id.person_name);
      TextView mobile_number=(TextView)rootView.findViewById(R.id.mobile_number);
      TextView address=(TextView)rootView.findViewById(R.id.address);
      TextView designation=(TextView)rootView.findViewById(R.id.designation);
      TextView email=(TextView)rootView.findViewById(R.id.email);


      person_name.setText(" Android ");
      mobile_number.setText("919638527410");
      address.setText("103,Ratanamani Complex, Asharom road, Ahmedabad");
      designation.setText("Android Developer");
      email.setText("abc@gmail.com");


        return rootView;
    }
}
