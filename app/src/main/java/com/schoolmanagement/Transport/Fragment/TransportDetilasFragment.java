package com.schoolmanagement.Transport.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.schoolmanagement.R;

public class TransportDetilasFragment extends Fragment {

    ImageView back,home;
    RecyclerView rv_noticeboard;
    RecyclerView.LayoutManager layoutManager;
    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public TransportDetilasFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }

    public TransportDetilasFragment(Activity homeTransportActivity, FragmentManager supportFragmentManager) {
        this.activity = homeTransportActivity;
        this.fragmentManager = supportFragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

      View  rootView = inflater.inflate(R.layout.transport_fragment_details, container, false);
        TextView bus_no=(TextView)rootView.findViewById(R.id.bus_no);
        TextView transport_date=(TextView)rootView.findViewById(R.id.transport_date);
        TextView area=(TextView)rootView.findViewById(R.id.area);
        TextView route=(TextView)rootView.findViewById(R.id.route);
        TextView time=(TextView)rootView.findViewById(R.id.time);

        bus_no.setText("RT0000");
        transport_date.setText("10/30/2020");
        area.setText("Ahmedabad");
        route.setText("Asharom  Road to  Nikol");
        time.setText("10 PM");

     return rootView;
  }
}
