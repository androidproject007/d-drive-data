package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.AttandenceAdapter;
import com.schoolmanagement.Student.Model.AttandenceList;

import java.util.ArrayList;
import java.util.List;

public class AttandenceFragment extends Fragment {

    Spinner payment_type_spinner;
    List<String> monthlist = new ArrayList<>();
    RecyclerView attandance_list;
    RecyclerView.LayoutManager layoutManager;
    AttandenceAdapter adapter;
    List<AttandenceList> attandenceLists= new ArrayList<>();
    ImageView home,back;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public AttandenceFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
     View   rootView = inflater.inflate(R.layout.student_fragment_attandance, container, false);
     CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Attandance");

        payment_type_spinner=(Spinner)rootView.findViewById(R.id.payment_type_spinner);
        attandance_list=(RecyclerView)rootView.findViewById(R.id.attandance_list);
        layoutManager= new LinearLayoutManager(activity);
        attandance_list.setLayoutManager(layoutManager);
        attandenceLists= new ArrayList<>();
        attandenceLists.add(new AttandenceList("1","1/3/2021","0"));
        attandenceLists.add(new AttandenceList("2","2/3/2021","1"));
        attandenceLists.add(new AttandenceList("3","3/3/2021","0"));
        attandenceLists.add(new AttandenceList("4","4/3/2021","0"));
        attandenceLists.add(new AttandenceList("5","5/3/2021","1"));
        attandenceLists.add(new AttandenceList("6","10/4/2021","0"));
        attandenceLists.add(new AttandenceList("7","10/4/2021","0"));
        adapter= new AttandenceAdapter(activity,attandenceLists);
        attandance_list.setAdapter(adapter);

        monthlist.add("Select Month");
        monthlist.add("January");
        monthlist.add("February");
        monthlist.add("March");

        ArrayAdapter<String> arrayAdaptera = new ArrayAdapter<String>(activity, R.layout.spinneritemprofile, R.id.spinnertextprofile, monthlist);
        payment_type_spinner.setAdapter(arrayAdaptera);
        payment_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (!statecheck.equals("api")) {
                if(position != 0){
                    if(monthlist.size()>0){
//                        pro_id = productLists.get(position - 1).getProductId();
//                        Product= productLists.get(position - 1).getProductName();
//                        if(Product.equals("Other")){
//                            l1_other_product.setVisibility(View.VISIBLE);
//                        }else {
//                            et_other_product.setText("");
//                            l1_other_product.setVisibility(View.GONE);
//                        }
                }
                    //Log.d("mytag","Live pro_id"+ pro_id);
                }else {
                   // pro_id="0";
                    //Log.d("mytag","livepro_id"+ pro_id);
                    //
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return rootView;
    }
}
