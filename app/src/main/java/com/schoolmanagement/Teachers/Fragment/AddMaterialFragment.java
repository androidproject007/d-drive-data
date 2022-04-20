package com.schoolmanagement.Teachers.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AddMaterialFragment extends Fragment{

    ImageView back,home;
    EditText topic_name;
    Spinner select_std,select_division,select_type;
    List<String> calsslist = new ArrayList<>();
    List<String> divisionlist = new ArrayList<>();
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;


    public AddMaterialFragment(Activity activity, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_activity_add_matireal, container, false);
        CustomHeaderWithRelative.setInnerToolbar(getActivity(), header, "Material");

        back=(ImageView)rootView.findViewById(R.id.back);
        home=(ImageView)rootView.findViewById(R.id.home);
        topic_name=(EditText)rootView.findViewById(R.id.topic_name);
        select_std=(Spinner)rootView.findViewById(R.id.select_std);

        calsslist.add("Select Class");
        calsslist.add("1st Class");
        calsslist.add("2st Class");
        calsslist.add("3st Class");
        calsslist.add("4st Class");
        calsslist.add("5st Class");
        calsslist.add("7st Class");

        ArrayAdapter<String> arrayAdaptera = new ArrayAdapter<String>(activity, R.layout.spinneritemprofile, R.id.spinnertextprofile, calsslist);
        select_std.setAdapter(arrayAdaptera);

        select_std.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (!statecheck.equals("api")) {
                if(position != 0){
                    if(calsslist.size()>0){
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
//        select_type=(Spinner)findViewById(R.id.select_type);
//        typelist.add("Select Type");
//        typelist.add("Home Work");
//        typelist.add("Assignment");
//
//        ArrayAdapter<String> arrayAdapterb = new ArrayAdapter<String>(AddMaterialActivity.this, R.layout.spinneritemprofile, R.id.spinnertextprofile, typelist);
//        select_type.setAdapter(arrayAdapterb);
//        select_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                if (!statecheck.equals("api")) {
//                if(position != 0){
//                    if(typelist.size()>0){
////                        pro_id = productLists.get(position - 1).getProductId();
////                        Product= productLists.get(position - 1).getProductName();
////                        if(Product.equals("Other")){
////                            l1_other_product.setVisibility(View.VISIBLE);
////                        }else {
////                            et_other_product.setText("");
////                            l1_other_product.setVisibility(View.GONE);
////                        }
//                    }
//                    //Log.d("mytag","Live pro_id"+ pro_id);
//                }else {
//                    // pro_id="0";
//                    //Log.d("mytag","livepro_id"+ pro_id);
//                    //
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        select_division=(Spinner)rootView.findViewById(R.id.select_division);
        divisionlist.add("Select Class");
        divisionlist.add("A");
        divisionlist.add("B");
        divisionlist.add("C");
        ArrayAdapter<String> arrayAdapterc = new ArrayAdapter<String>(activity, R.layout.spinneritemprofile, R.id.spinnertextprofile, divisionlist);
        select_division.setAdapter(arrayAdapterc);

        select_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (!statecheck.equals("api")) {
                if(position != 0){
                    if(divisionlist.size()>0){
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

//        date_select=(EditText) findViewById(R.id.date_select);
//        date_select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//                final Calendar newDate = Calendar.getInstance();
//                datePickerDialog = new DatePickerDialog(AddMaterialActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        newDate.set(year, monthOfYear, dayOfMonth);
//                        date_select.setText(dateFormat.format(newDate.getTime()));
//                    }
//                }, 2021, newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH));
//                Calendar calendar = Calendar.getInstance();
//                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//                date_select.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // TODO Auto-generated method stub
//                        datePickerDialog.show();
//                    }
//                });
//            }
//        });

        return  rootView;
    }
}
