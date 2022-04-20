package com.schoolmanagement.Teachers.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.Other.MyProgressDialog;
import com.schoolmanagement.R;

public class TeachersHomeFragment extends Fragment {

    public Context context;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;
    private static final int SWIPE_MIN_DISTANCE = 150;
    private static final int SWIPE_THRESHOLD_VELOCITY = 250;
    private MyProgressDialog myProgressDialog;

    public TeachersHomeFragment(Context context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_home_fragment, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Home");
        return rootView;
    }

    public void pushFragment(Fragment frag, String tag, Boolean addtobackstack) {
//            FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.rl_content, frag, tag);
        if(addtobackstack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
