package com.schoolmanagement.Parents;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.schoolmanagement.MainActivity;
import com.schoolmanagement.Other.MyProgressDialog;
import com.schoolmanagement.Other.Prefs;
import com.schoolmanagement.Parents.Fragment.HomeParentsFragment;
import com.schoolmanagement.Parents.Fragment.ProfileParentsFragment;
import com.schoolmanagement.Parents.Fragment.TrasportMapFragment;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.AdapterMenu;
import com.schoolmanagement.Student.Fragment.AttandenceFragment;
import com.schoolmanagement.Student.Fragment.ExamResultFragment;
import com.schoolmanagement.Student.Fragment.FessFragment;
import com.schoolmanagement.Student.Fragment.HomeWorkFragment;
import com.schoolmanagement.Student.Fragment.TimeTableFragment;
import com.schoolmanagement.Student.Model.info;

import java.util.ArrayList;



public class ParentsHomeActivity extends AppCompatActivity {
    ArrayList<info> menuarryList = new ArrayList<>();
    public RelativeLayout header;
    private RecyclerView rv_menu;
    public DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    AdapterMenu adapterMenu;
    FrameLayout rl_content;
    String[] menuTitle = {"Home","Attandance","Daily Homework","Time Table","Fess","Exam Results","Trasport","Profile","Settings"};
    public static FragmentManager fm;
    public Context context;
    Drawable[] menuIcon;
    ImageView iv_toggle;
    TextView tv_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_home);
        fm = getSupportFragmentManager();
        context= ParentsHomeActivity.this;
        menuIcon = new Drawable[]{context.getResources().getDrawable(R.drawable.home),
                context.getResources().getDrawable(R.drawable.attendance),
                context.getResources().getDrawable(R.drawable.daily_homework),
                context.getResources().getDrawable(R.drawable.time_table),
                context.getResources().getDrawable(R.drawable.fees),
                context.getResources().getDrawable(R.drawable.exam),
                context.getResources().getDrawable(R.drawable.transport),
                context.getResources().getDrawable(R.drawable.profile),
                context.getResources().getDrawable(R.drawable.settings)};

            init();
    }

    public void init(){


        rv_menu = findViewById(R.id.rv_menu);
        rv_menu.setLayoutManager(new LinearLayoutManager(ParentsHomeActivity.this));
        tv_logout = (TextView) findViewById(R.id.tv_logout);
        drawer = (DrawerLayout) findViewById(R.id.rec1_header);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.open_Drawer, R.string.close_Drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        iv_toggle = (ImageView) findViewById(R.id.menu);
        rl_content = (FrameLayout) findViewById(R.id.rl_content);
        header = findViewById(R.id.rl_header_main);

        for(int i = 0; i < menuTitle.length; i++){
            menuarryList.add(new info(menuTitle[i], menuIcon[i]));
        }

        adapterMenu = new AdapterMenu(ParentsHomeActivity.this, menuarryList);
        rv_menu.setAdapter(adapterMenu);

        listner();

        pushFragment(new HomeParentsFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
    }

    public void listner(){
//        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                if(model.getId()==1){
////                    Toast.makeText(HomeActivity.this, "Home ", Toast.LENGTH_SHORT).show();
//                    pushFragment(new HomeFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
//                }else  if(model.getId()==2) {
//                   // Toast.makeText(HomeActivity.this, "Time Table", Toast.LENGTH_SHORT).show();
//                    pushFragment(new TimeTableFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
//                   // drawer.closeDrawer(GravityCompat.START);
//                }else  if(model.getId()==3){
//                    pushFragment(new NotificationFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                    Toast.makeText(HomeActivity.this, "Notification", Toast.LENGTH_SHORT).show();
//                }else  if(model.getId()==4){
//                    pushFragment(new ProfileFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                    //drawer.closeDrawer(GravityCompat.START);
//                   // Toast.makeText(HomeActivity.this, "profile", Toast.LENGTH_SHORT).show();
//                }
//                // YOUR CODES
//                return null;
//            }
//        });

        iv_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                drawer.openDrawer(GravityCompat.START);
            }
        });

        tv_logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ParentsHomeActivity.this);
                alertDialog.setTitle("School Management");
                alertDialog.setMessage("Are you sure want to Logout?");
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                Prefs.getPrefInstance().setValue(ParentsHomeActivity.this,"user_id",null);
                                Intent i= new Intent(ParentsHomeActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                                dialog.dismiss();
                                //  logoutapi();
                            }
                        });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        rv_menu.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                info menu = (info) menuarryList.get(position);
                info Selectname;
                String nameSelect="";

                if(adapterMenu.getSelectedItem()!=-1){
                    Selectname = menuarryList.get(adapterMenu.getSelectedItem());
                    nameSelect= Selectname.getName();
                }

                adapterMenu.setSelectedItem(position);
                String name = menu.getName();
                Log.d("mytag","name  "+name+"Selectname "+nameSelect);
                if(name==nameSelect){
                    drawer.closeDrawer(GravityCompat.START);
                }else {
                    switch(name){
                        case "Home":
                            pushFragment(new HomeParentsFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Attandance":
                            pushFragment(new AttandenceFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Daily Homework":
                            //pushFragment(new AddLiveStockFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer, "0"), "Home", false);
                            pushFragment(new HomeWorkFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer, "1"), "Home", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Time Table":
                            pushFragment(new TimeTableFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
                            drawer.closeDrawer(GravityCompat.START);
                             break;
                        case "Trasport":
                            pushFragment(new TrasportMapFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer,"1"), "Home12", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Fess":
                            pushFragment(new FessFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Exam Results":
                            pushFragment(new ExamResultFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer, "0"), "Home12", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Profile":
                            pushFragment(new ProfileParentsFragment(ParentsHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
                            drawer.closeDrawer(GravityCompat.START);
                          break;


//                     case "Global Platform":
//                            pushFragment(new ContactUsFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                            drawer.closeDrawer(GravityCompat.START);
//                            break;
                    }
                }
            }
        }));
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pushFragment(new HomeFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                adapterMenu.setSelectedItem(0);
//            }
//        });

//        livestock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(HomeActivity.this, ActivityLiveStock.class);
//                startActivity(intent);
//                //pushFragment(new AddLiveStockFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer,"0"), "Home12", false);
//                adapterMenu.setSelectedItem(3);
//            }
//        });


//        freebuyers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pushFragment(new BuyersFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer,"0"), "Home12", false);
//                adapterMenu.notifyDataSetChanged();
//                adapterMenu.setSelectedItem(5);
//            }
//        });
//
//        freeseller.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pushFragment(new SellerFRagment(HomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                adapterMenu.setSelectedItem(6);
//            }
//        });
    }

    public void pushFragment(Fragment frag, String tag, Boolean addtobackstack){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

//        if(fm.findFragmentById(R.id.content)!=null) {
//            transaction.remove(fm.findFragmentById(R.id.content));
//
//            Log.d("mytag","removepushfragment"+ fm.findFragmentById(R.id.content));
//        }
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.replace(R.id.rl_content, frag, tag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        if (addtobackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
//        transaction.commit();
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        GestureDetector mGestureDetector;
        private OnItemClickListener mListener;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener){
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);
        }
    }

}
