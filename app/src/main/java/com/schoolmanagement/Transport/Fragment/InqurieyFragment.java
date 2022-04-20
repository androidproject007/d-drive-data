package com.schoolmanagement.Transport.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.schoolmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class InqurieyFragment extends Fragment {
    ImageView back,home;
    RecyclerView rv_noticeboard;
    RecyclerView.LayoutManager layoutManager;
    TextView header_name;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public InqurieyFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    public InqurieyFragment(Activity homeTransportActivity, FragmentManager supportFragmentManager) {
        this.activity = homeTransportActivity;
        this.fragmentManager = supportFragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View   rootView = inflater.inflate(R.layout.transport_fragment_inquiry, container, false);
        EditText et_name = (EditText)rootView.findViewById(R.id.et_name);
        EditText phone=(EditText)rootView.findViewById(R.id.phone);
        EditText bus_no=(EditText)rootView.findViewById(R.id.bus_no);
        EditText bus_time=(EditText)rootView.findViewById(R.id.bus_time);
        EditText route=(EditText)rootView.findViewById(R.id.route);
        EditText note=(EditText)rootView.findViewById(R.id.note);
        Button btn_submit=(Button)rootView.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= et_name.getText().toString().trim();
                String mobile = phone.getText().toString().trim();
                String bs_no= bus_no.getText().toString().trim();
                String bs_time =  bus_time.getText().toString().trim();
                String Route=  route.getText().toString().trim();
                String notea= note.getText().toString().trim();

                if(name.isEmpty()){
                    Toast.makeText(activity, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }else if(mobile.isEmpty()){
                    Toast.makeText(activity, "Please Enter Mobile", Toast.LENGTH_SHORT).show();
                }else if(bs_no.isEmpty()){
                    Toast.makeText(activity, "Please Enter Bus No.", Toast.LENGTH_SHORT).show();
                }else if(bs_time.isEmpty()){
                    Toast.makeText(activity, "Please Enter Bus Time", Toast.LENGTH_SHORT).show();
                }else  if(Route.isEmpty()){
                    Toast.makeText(activity, "Please Enter Route", Toast.LENGTH_SHORT).show();
                }else if(notea.isEmpty()){
                    Toast.makeText(activity, "Please Enter Note", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(activity, "Data Add Succesfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}
