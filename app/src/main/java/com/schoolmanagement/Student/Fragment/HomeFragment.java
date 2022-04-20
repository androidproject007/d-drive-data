package com.schoolmanagement.Student.Fragment;

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

public class HomeFragment extends Fragment {

    public Context context;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    private ViewFlipper mViewFlipper,mViewFlipper1,mViewFlipper2;
    Drawable[] menuIcon,menuIcon2,menuIcon3;
    private static final int SWIPE_MIN_DISTANCE = 150;
    private static final int SWIPE_THRESHOLD_VELOCITY = 250;
    View rootView;
    private MyProgressDialog myProgressDialog;

    public HomeFragment(Context context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.student_fragment_home, container, false);

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

    class SwipeGestureDetector extends  GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.left_out));
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.right_out));
                    mViewFlipper.showPrevious();
                    return true;
                }
            } catch(Exception e){
                e.printStackTrace();
            }
           return false;
        }
    }

}
