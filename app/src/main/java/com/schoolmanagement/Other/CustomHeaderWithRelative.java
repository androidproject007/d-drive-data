package com.schoolmanagement.Other;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.schoolmanagement.R;


public class CustomHeaderWithRelative {

    public static void setOuter(final Activity activity, final DrawerLayout drawerLayout, RelativeLayout relativeLayout, String title){
        relativeLayout=activity.findViewById(R.id.rl_header_main);
        relativeLayout.setVisibility(View.VISIBLE);
        relativeLayout.setTag("Outer");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        final ImageView iv_menu = (ImageView) relativeLayout.findViewById(R.id.menu);
        iv_menu.setImageDrawable(activity.getResources().getDrawable(R.drawable.menu));
        final TextView tv_title = (TextView) relativeLayout.findViewById(R.id.member_name);

      //  final ImageView opation = (ImageView) relativeLayout.findViewById(R.id.option);

//        opation.setImageDrawable(activity.getResources().getDrawable(R.drawable.option));
//        final TextView text = (TextView) relativeLayout.findViewById(R.id.text);
//        text.setText(R.string.Add);

//        if(title.equals("Tiles Visiting Card")||title.equals("My Employment")||title.equals("My Skills")||title.equals("My Family")){
//            opation.setVisibility(View.VISIBLE);
//            text.setVisibility(View.GONE);
//        }else {
//            opation.setVisibility(View.GONE);
//            text.setVisibility(View.GONE);
//        }

        tv_title.setSelected(true);
        tv_title.setText(title);
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public static void setInnerToolbar(final Activity activity, RelativeLayout relativeLayout, String title) {
        relativeLayout.setTag("Inner");
        relativeLayout.setVisibility(View.VISIBLE);
        relativeLayout=activity.findViewById(R.id.rl_header_main);
        ImageView iv_menu = (ImageView) relativeLayout.findViewById(R.id.menu);
        final TextView tv_title = (TextView) relativeLayout.findViewById(R.id.member_name);
        tv_title.setText(title);
     //   final ImageView opation = (ImageView) relativeLayout.findViewById(R.id.option);
        iv_menu.setImageDrawable(activity.getResources().getDrawable(R.drawable.back));
        iv_menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                activity.onBackPressed();
            }
        });
    }
}
