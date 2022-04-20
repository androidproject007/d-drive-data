package com.schoolmanagement.Parents.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.Other.MyProgressDialog;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Fragment.AttandenceFragment;
import com.schoolmanagement.Student.Fragment.ExamResultFragment;
import com.schoolmanagement.Student.Fragment.ExamResultListFragment;
import com.schoolmanagement.Student.Fragment.HomeWorkFragment;
import com.schoolmanagement.Transport.Fragment.TransportDetilasFragment;


public class HomeParentsFragment extends Fragment {
    public Activity context;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;
    View rootView;
    private MyProgressDialog myProgressDialog;
    CardView attandance,exam_result,home_work,trasport;
    TextView change_Child;
    AlertDialog.Builder builder;

    public HomeParentsFragment(Activity  context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
      View  rootView = inflater.inflate(R.layout.parents_fragment_home, container, false);
        CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Home");

        attandance=(CardView)rootView.findViewById(R.id.attandance);
        attandance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new AttandenceFragment(context, fragmentManager, header, drawer), "Education", true);
            }
        });

        exam_result=(CardView)rootView.findViewById(R.id.exam_result);
        exam_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pushFragment(new ExamResultFragment(context, fragmentManager, header, drawer,"1"), "Education", true);
            }
        });

        home_work=(CardView)rootView.findViewById(R.id.home_work);
        home_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pushFragment(new HomeWorkFragment(context, fragmentManager, header, drawer,"1"), "Education", true);
            }
        });

        trasport=(CardView)rootView.findViewById(R.id.trasport);
        trasport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pushFragment(new TrasportMapFragment(context, fragmentManager, header, drawer,"0"), "Education", true);
            }
        });

        change_Child=(TextView)rootView.findViewById(R.id.change_Child);

        change_Child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i= new Intent()
                builder = new AlertDialog.Builder(context)
                .setTitle("Change Student")
                .setMessage("Please choose Child")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Yes Click", Toast.LENGTH_SHORT).show();
                    }
                })

               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "No Click", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        return rootView;
    }


    public void pushFragment(Fragment frag, String tag, Boolean addtobackstack) {
//            FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.rl_content, frag, tag);
        if (addtobackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
