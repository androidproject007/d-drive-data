package com.schoolmanagement.Transport;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.schoolmanagement.R;
import com.schoolmanagement.Transport.Fragment.InqurieyFragment;
import com.schoolmanagement.Transport.Fragment.ProfileTransportFragment;
import com.schoolmanagement.Transport.Fragment.Student_List_Fragment;
import com.schoolmanagement.Transport.Fragment.TransportDetilasFragment;
import com.schoolmanagement.Transport.Fragment.TrasportMapNavigationFragment;


import java.util.ArrayList;

public class HomeTransportActivity extends AppCompatActivity {

    public static FragmentManager fm;
    public Context context;
    BottomNavigationBar bottomNavigation;
    FrameLayout rl_content;
    ImageView profile;
    TextView text_home;
    private ArrayList<Fragment> fragments= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport_home_activity);
        fm = getSupportFragmentManager();
        text_home=(TextView)findViewById(R.id.text_home);
        text_home.setText("Home");
        context = HomeTransportActivity.this;
        bottomNavigation=(BottomNavigationBar)findViewById(R.id.bottomNavigation);

        init();
        bottomNavigation
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_bus, "Transport"))
                .addItem(new BottomNavigationItem(R.drawable.user, "Student List"))
                .addItem(new BottomNavigationItem(R.drawable.ic_open_book, "inquiry"))
                .setFirstSelectedPosition(0)
                .initialise();

     //   bottomNavigation.set

        getFragments();

        bottomNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position){
                if (fragments != null) {
                    if (position < fragments.size()){
                        if(position==0){
                            text_home.setText("Home");
                        }else if(position==1){
                            text_home.setText("Transport Details");
                        }else if(position==2){
                            text_home.setText("Student List");
                        }else  if(position==3){
                            text_home.setText("Inquiry");
                        }

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                       // fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        Fragment fragment = fragments.get(position);
//                        if (fragment.isAdded()) {
                            ft.replace(R.id.rl_content, fragment);
//                        } else {
//                            ft.add(R.id.rl_content, fragment);
//                        }
                        ft.commitAllowingStateLoss();
                    }
                }

            }
            @Override
            public void onTabUnselected(int position) {
//                if (fragments != null) {
//                    if(position < fragments.size()){
//
//                        FragmentManager fm = getSupportFragmentManager();
//                        FragmentTransaction ft = fm.beginTransaction();
//                        Fragment fragment = fragments.get(position);
//                        ft.remove(fragment);
//                        ft.commitAllowingStateLoss();
//                    }
//                }
            }
            @Override
            public void onTabReselected(int position) {
            }
        });

//        bottomNavigation.set
        //bottomNavigation=(MeowBottomNavigation)findViewById(R.id.bottomNavigation);
      //  bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
     // //  bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_open_book));
      //  bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_homework));
      //  bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_document));

    }
    public void init(){

        rl_content = (FrameLayout) findViewById(R.id.rl_content);
        profile=(ImageView)findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_home.setText("Profile");

                pushFragment(new ProfileTransportFragment(HomeTransportActivity.this, getSupportFragmentManager()), "Home", false);
            }
        });
        listner();
        pushFragment(new TrasportMapNavigationFragment(HomeTransportActivity.this, getSupportFragmentManager()), "Home", false);
  }


//    private void setDefaultFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.layFrame, HomeFragment.newInstance("Home"));
//        transaction.commit();
//    }


    public void listner(){
//        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                if (model.getId() == 1) {
////                    Toast.makeText(HomeTransportActivity.this, "Home ", Toast.LENGTH_SHORT).show();
//                   // pushFragment(new HomeFragment(HomeTransportActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
//                } else if (model.getId() == 2) {
//                    pushFragment(new InqurieyFragment(HomeTransportActivity.this, getSupportFragmentManager()), "Home", false);
//                    // Toast.makeText(HomeTransportActivity.this, "Time Table", Toast.LENGTH_SHORT).show();
//                   // pushFragment(new TimeTableFragment(HomeTransportActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
//                    // drawer.closeDrawer(GravityCompat.START);
//                } else if (model.getId() == 3) {
//                    pushFragment(new Student_List_Fragment(HomeTransportActivity.this, getSupportFragmentManager()), "Home12", false);
//                    Toast.makeText(HomeTransportActivity.this, "Notification", Toast.LENGTH_SHORT).show();
//                } else if (model.getId() == 4) {
//                   // pushFragment(new ProfileFragment(HomeTransportActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                    //drawer.closeDrawer(GravityCompat.START);
//                    // Toast.makeText(HomeTransportActivity.this, "profile", Toast.LENGTH_SHORT).show();
//                }
//                // YOUR CODES
//                return null;
//            }
//        });

//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                if(model.getId() == 1){
////                    Toast.makeText(HomeTransportActivity.this, "Home ", Toast.LENGTH_SHORT).show();
//                 pushFragment(new TrasportMapNavigationFragment(HomeTransportActivity.this, getSupportFragmentManager()), "Home", false);
//
//                } else if (model.getId() == 2) {
//                    // Toast.makeText(HomeTransportActivity.this, "Time Table", Toast.LENGTH_SHORT).show();
//                    pushFragment(new InqurieyFragment(HomeTransportActivity.this, getSupportFragmentManager()), "Home", false);
//                    // drawer.closeDrawer(GravityCompat.START);
//                } else if (model.getId() == 3) {
//                  //  pushFragment(new NotificationFragment(HomeTransportActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                    Toast.makeText(HomeTransportActivity.this, "Notification", Toast.LENGTH_SHORT).show();
//                } else if (model.getId() == 4) {
//                  //  pushFragment(new ProfileFragment(HomeTransportActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                    //drawer.closeDrawer(GravityCompat.START);
//                    // Toast.makeText(HomeTransportActivity.this, "profile", Toast.LENGTH_SHORT).show();
//                }
//                // YOUR CODES
//                return null;
//            }
//        });
//        tv_logout.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeTransportActivity.this);
//                alertDialog.setTitle("School Management");
//                alertDialog.setMessage("Are you sure want to Logout?");
//                alertDialog.setPositiveButton("YES",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Prefs.getPrefInstance().setValue(HomeTransportActivity.this, "user_id", null);
//                                Intent i = new Intent(HomeTransportActivity.this, LoginActivity.class);
//                                startActivity(i);
//                                finish();
//                                dialog.dismiss();
//                                //  logoutapi();
//                            }
//                        });
//                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                alertDialog.show();
//            }
//        });
    }

    public void pushFragment(Fragment frag, String tag, Boolean addtobackstack){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.replace(R.id.rl_content, frag, tag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if(addtobackstack){
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
//        transaction.commit();
    }

    private ArrayList<Fragment> getFragments(){
        fragments.add(new TrasportMapNavigationFragment(this, getSupportFragmentManager()));
        fragments.add(new TransportDetilasFragment(this,getSupportFragmentManager()));
        fragments.add(new Student_List_Fragment(this,getSupportFragmentManager()));
        fragments.add(new InqurieyFragment(this,getSupportFragmentManager()));
        return fragments;
    }


//    private void setScrollableText(int position) {
//        switch (position) {
//            case 0:
//                getSupportFragmentManager().beginTransaction().replace(R.id.rl_content, fragment1).commitAllowingStateLoss();
//                break;
//            case 1:
//                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment2).commitAllowingStateLoss();
//                break;
//            case 2:
//                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment3).commitAllowingStateLoss();
//                break;
//            case 3:
//                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment4).commitAllowingStateLoss();
//                break;
//            case 4:
//                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment5).commitAllowingStateLoss();
//                break;
//            default:
//                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment6).commitAllowingStateLoss();
//                break;
//        }
//    }




}
