package com.schoolmanagement.Transport.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.R;
import com.schoolmanagement.Transport.Adapter.StudentListTransportAdapter;


public class Student_List_Fragment extends Fragment {

    RecyclerView recyclerview_student_list;
    RecyclerView.LayoutManager layoutManager;
    public Context context;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    StudentListTransportAdapter adapter;

    public Student_List_Fragment(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View  rootView = inflater.inflate(R.layout.transport_student_list, container, false);
        recyclerview_student_list=(RecyclerView)rootView.findViewById(R.id.recyclerview_student_list);
        layoutManager= new LinearLayoutManager(getActivity());
        recyclerview_student_list.setLayoutManager(layoutManager);
        adapter= new StudentListTransportAdapter(getActivity());
        recyclerview_student_list.setAdapter(adapter);
        return  rootView;
    }
}
