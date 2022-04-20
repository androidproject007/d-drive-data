package com.schoolmanagement.Teachers.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;


public class AttandanceFragment extends Fragment {

   ImageView back,home;
   LinearLayout add_attandance;
   LinearLayout all_attendance;
   public Activity activity;
   public FragmentManager fragmentManager;
   RelativeLayout header;
   DrawerLayout drawer;
   View rootView;
   EditText et_date;

    public AttandanceFragment(Activity activity, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
            this.activity = activity;
            this.fragmentManager = fragmentManager;
            this.header = lin_header;
            this.drawer = drawer;
     }

      @Override
       public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_activity_attendance, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(),drawer, header, "Add Attandance");

        et_date=(EditText)rootView.findViewById(R.id.et_date);
        add_attandance=(LinearLayout)rootView.findViewById(R.id.add_attandance);
        all_attendance=(LinearLayout)rootView.findViewById(R.id.all_attendance);
        add_attandance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new AddAttandanceFragment(activity, fragmentManager, header, drawer), "Education", true);

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
