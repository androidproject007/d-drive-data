package com.schoolmanagement.Teachers.Fragment;

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

public class ProfileTeachersFragment extends Fragment {

    RecyclerView.LayoutManager layoutManager;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;
    TextView name,mobile,address,parent_mobile,email,salary,disination;

    public ProfileTeachersFragment(Activity activity, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_activity_profile, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(),drawer, header, "Profile");

        name=(TextView)rootView.findViewById(R.id.name);
        mobile=(TextView)rootView.findViewById(R.id.mobile);
        address=(TextView)rootView.findViewById(R.id.address);
        parent_mobile=(TextView)rootView.findViewById(R.id.parent_mobile);
        email=(TextView)rootView.findViewById(R.id.email);
        salary=(TextView)rootView.findViewById(R.id.salary);
        disination=(TextView)rootView.findViewById(R.id.disination);

 //   header_name=(TextView)findViewById(R.id.header_name);
//        back=(ImageView)rootView.findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//
//        home=(ImageView)findViewById(R.id.home);
//        home.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent i= new Intent(ProfileTeachersActivity.this, HomeActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });

     return  rootView;
    }
}
