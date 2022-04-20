package com.schoolmanagement.Teachers.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.schoolmanagement.Teachers.Adapter.AddAttandanceAdapter;
import com.schoolmanagement.Teachers.Model.Attandancelist;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddAttandanceFragment extends Fragment {

    ImageView back,home;
    EditText date_select,serach;
    Spinner select_calss,select_division;
    List<String> calsslist = new ArrayList<>();
    List<String> divisionlist = new ArrayList<>();
    List<Attandancelist> items= new ArrayList<>();
    RecyclerView recycelerview_add_attandance;
    RecyclerView.LayoutManager layoutManager;
    AddAttandanceAdapter addAttandanceAdapter;
    boolean isOnTextChanged = false;
    SimpleDateFormat dateFormat;
    DatePickerDialog datePickerDialog;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;


    public AddAttandanceFragment(Activity activity, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_activity_add_attendance, container, false);
        CustomHeaderWithRelative.setInnerToolbar(getActivity(), header, "Add Attandance");

        date_select=(EditText)rootView.findViewById(R.id.date_select);
        date_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                final Calendar newDate = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        newDate.set(year, monthOfYear, dayOfMonth);
                        date_select.setText(dateFormat.format(newDate.getTime()));
                    }
                }, 2021, newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH));

                Calendar calendar = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                date_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        // TODO Auto-generated method stub
                        datePickerDialog.show();
                    }
                });
            }
        });

        select_calss=(Spinner)rootView.findViewById(R.id.select_calss);
        calsslist.add("Select Class");
        calsslist.add("1st Class");
        calsslist.add("2st Class");
        calsslist.add("3st Class");
        calsslist.add("4st Class");
        calsslist.add("5st Class");
        calsslist.add("7st Class");

        ArrayAdapter<String> arrayAdaptera = new ArrayAdapter<String>(activity, R.layout.spinneritemprofile, R.id.spinnertextprofile, calsslist);
        select_calss.setAdapter(arrayAdaptera);
        select_calss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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

        recycelerview_add_attandance=(RecyclerView)rootView.findViewById(R.id.recycelerview_add_attandance);
        layoutManager= new LinearLayoutManager(activity);
        recycelerview_add_attandance.setLayoutManager(layoutManager);

        items= new ArrayList<>();
        items.add(new Attandancelist("1","Abc","0","10/4/2020"));
        items.add(new Attandancelist("1","BCD","0","11/4/2020"));
        items.add(new Attandancelist("1","EFG","0","12/4/2020"));
        items.add(new Attandancelist("1","HIJ","0","13/4/2020"));
        items.add(new Attandancelist("1","KLM","0","14/4/2020"));


        addAttandanceAdapter= new AddAttandanceAdapter(activity,items);
        recycelerview_add_attandance.setAdapter(addAttandanceAdapter);
        serach=(EditText)rootView.findViewById(R.id.serach);
        serach.addTextChangedListener(new TextWatcher() {
            String v_qty = serach.getText().toString().trim();
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                isOnTextChanged = true;
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable arg0){
                // TODO Auto-generated method stub
                String v_qty1 = serach.getText().toString().trim();
//                    if(arg0.toString().trim().length()>0) {
                if(items.size()>0){
                    addAttandanceAdapter.getFilter().filter(arg0.toString().trim());
                }
            }
        });
        return rootView;
    }
}
