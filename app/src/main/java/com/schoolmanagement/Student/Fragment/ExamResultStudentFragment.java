package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Adapter.SubjectExamAdapter;


import java.util.ArrayList;
import java.util.List;

public class ExamResultStudentFragment extends Fragment {

    ImageView back,home;
    Button add_home_work;
    RecyclerView subject;
    RecyclerView.LayoutManager layoutManager;
    SubjectExamAdapter adapter;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.teachers_activity_exam_result_individually);

    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;

    public ExamResultStudentFragment(Activity activity, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.teachers_activity_exam_result_individually, container, false);
        CustomHeaderWithRelative.setInnerToolbar(getActivity(), header, "Exam Result");

        back=(ImageView)rootView.findViewById(R.id.back);
        home=(ImageView)rootView.findViewById(R.id.home);

        AnyChartView anyChartView = rootView.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(rootView.findViewById(R.id.progress_bar));


        Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(activity, event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Maths", 6371664));
        data.add(new ValueDataEntry("English", 789622));
        data.add(new ValueDataEntry("Hindi", 7216301));
        data.add(new ValueDataEntry("Social Science", 1486621));
        data.add(new ValueDataEntry("Geography", 1200000));

        pie.data(data);

        pie.title("1th Exam Result");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Retail channels")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);


        subject=(RecyclerView)rootView.findViewById(R.id.subject);
        layoutManager= new LinearLayoutManager(activity);
        subject.setLayoutManager(layoutManager);
        adapter= new SubjectExamAdapter(activity);
        subject.setAdapter(adapter);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               onBackPressed();
//            }
//        });
//
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                 Intent i = new Intent(ExamResultStudentActivity.this,HomeActivityteachers.class);
//                 startActivity(i);
//                 finish();
//            }
//        });
        return  rootView;
    }
}
