package com.schoolmanagement.Teachers.Activity;

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
import android.widget.Toast;

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
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.AdapterMenu;
import com.schoolmanagement.Student.Fragment.EventFragment;
import com.schoolmanagement.Student.Fragment.ExamResultListFragment;
import com.schoolmanagement.Student.Fragment.HomeWorkFragment;
import com.schoolmanagement.Student.Fragment.TimeTableFragment;
import com.schoolmanagement.Student.HomeActivity;
import com.schoolmanagement.Student.Model.info;
import com.schoolmanagement.Teachers.Fragment.AllMaterialFragment;
import com.schoolmanagement.Teachers.Fragment.AttandanceFragment;
import com.schoolmanagement.Teachers.Fragment.DailyHomeworkFragment;
import com.schoolmanagement.Teachers.Fragment.ProfileTeachersFragment;
import com.schoolmanagement.Teachers.Fragment.TeachersHomeFragment;

import java.util.ArrayList;


public class TeachersHomeActivity extends AppCompatActivity {
    ArrayList<info> menuarryList = new ArrayList<>();
    public RelativeLayout header;
    //    LinearLayout rl_main;
    private RecyclerView rv_menu;
    public DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    AdapterMenu adapterMenu;
    FrameLayout rl_content;
    String[] menuTitle = {"Home","Attandance","Daily Homework","Time Table","Online Study","Exam Result","All Material","Event","Profile","Settings"};
    //Drawable[] menuIcon = {getApplicationContext().getResources().getDrawable(R.drawable.add_contact),getApplicationContext().getResources().getDrawable(R.drawable.buyer),getResources().getDrawable(R.drawable.seller),getApplicationContext().getResources().getDrawable(R.drawable.user),getApplicationContext().getResources().getDrawable(R.drawable.call)};
    public static FragmentManager fm;
    public Context context;
    Drawable[] menuIcon;
    MyProgressDialog myProgressDialog;
    LinearLayout livestock,freebuyers,freeseller,home;
    String currentVersion;
    ImageView iv_close, iv_home, iv_makeATrip, iv_menu, iv_toggle;
    TextView tv_logout;
    TextView tv_selecter_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_home);
        tv_selecter_name=(TextView)findViewById(R.id.tv_selecter_name);
        fm = getSupportFragmentManager();
        context= TeachersHomeActivity.this;
        menuIcon = new Drawable[]{context.getResources().getDrawable(R.drawable.home),context.getResources().getDrawable(R.drawable.attendance),context.getResources().getDrawable(R.drawable.daily_homework),context.getResources().getDrawable(R.drawable.time_table),context.getResources().getDrawable(R.drawable.online_study), context.getResources().getDrawable(R.drawable.online_study),context.getResources().getDrawable(R.drawable.all_material),context.getResources().getDrawable(R.drawable.events),context.getResources().getDrawable(R.drawable.profile), context.getResources().getDrawable(R.drawable.settings)};
        init();
    }

    public void init(){
//        rl_main = findViewById(R.id.rl_main);
        rv_menu = findViewById(R.id.rv_menu);
        rv_menu.setLayoutManager(new LinearLayoutManager(TeachersHomeActivity.this));
        tv_logout = (TextView) findViewById(R.id.tv_logout);
        drawer = (DrawerLayout) findViewById(R.id.rec1_header);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_Drawer, R.string.close_Drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        iv_toggle = (ImageView) findViewById(R.id.menu);
        rl_content = (FrameLayout) findViewById(R.id.rl_content);
        header = findViewById(R.id.rl_header_main);

        for(int i = 0; i < menuTitle.length; i++){
            menuarryList.add(new info(menuTitle[i], menuIcon[i]));
        }

        adapterMenu = new AdapterMenu(TeachersHomeActivity.this, menuarryList);
        rv_menu.setAdapter(adapterMenu);

        listner();

        pushFragment(new TeachersHomeFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
    }

    public void listner(){
        iv_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                drawer.openDrawer(GravityCompat.START);
            }
        });

        tv_logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TeachersHomeActivity.this);
                alertDialog.setTitle("School Management");
                alertDialog.setMessage("Are you sure want to Logout?");
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                Prefs.getPrefInstance().setValue(TeachersHomeActivity.this,"user_id",null);
                                Intent i= new Intent(TeachersHomeActivity.this, MainActivity.class);
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

       rv_menu.addOnItemTouchListener(new RecyclerItemClickListener(this, new HomeActivity.RecyclerItemClickListener.OnItemClickListener() {
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
                Log.d("mytag","name  "+name+"SelectName "+nameSelect);
                if(name==nameSelect){
                    drawer.closeDrawer(GravityCompat.START);
                }else {
                    switch(name){
                        case "Home":
                            pushFragment(new TeachersHomeFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Attandance":
                            pushFragment(new AttandanceFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Daily Homework":
                            pushFragment(new DailyHomeworkFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;

                        case "Time Table":
                          //  pushFragment(new Time_table_techersFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);

                            pushFragment(new TimeTableFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home", false);
                            drawer.closeDrawer(GravityCompat.START);

                            break;

                        case "Exam Result":

                            pushFragment(new ExamResultListFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
                            drawer.closeDrawer(GravityCompat.START);

                         break;


//                        case "Online Study":
//                            pushFragment(new Online_ZoomFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer,"1"), "Home12", false);
//                            drawer.closeDrawer(GravityCompat.START);
//                            break;
//                        case "Fess":
//                            pushFragment(new FessFragment(HomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
//                            drawer.closeDrawer(GravityCompat.START);
//                            break;
                        case "Event":
                            pushFragment(new EventFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "Profile":
                            pushFragment(new ProfileTeachersFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case "All Material":
                            pushFragment(new AllMaterialFragment(TeachersHomeActivity.this, getSupportFragmentManager(), header, drawer), "Home12", false);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                    }
                }
            }
        }));
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
        private HomeActivity.RecyclerItemClickListener.OnItemClickListener mListener;

        public RecyclerItemClickListener(Context context, HomeActivity.RecyclerItemClickListener.OnItemClickListener listener){
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e){
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)){
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
