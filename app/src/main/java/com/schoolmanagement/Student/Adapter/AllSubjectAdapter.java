package com.schoolmanagement.Student.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.MyProgressDialog;
import com.schoolmanagement.R;

import com.schoolmanagement.Student.Fragment.AllMaterialChapterFragment;
import com.schoolmanagement.Student.Model.AttandenceList;

import java.util.ArrayList;
import java.util.List;


public class AllSubjectAdapter extends RecyclerView.Adapter<AllSubjectAdapter.ViewHolder> {
    public Activity activity;
    public FragmentManager fragmentManager;
    LayoutInflater mInflater;
    RelativeLayout header;
    DrawerLayout drawer;
    MyProgressDialog myProgressDialog;
    ImageView roundedimag;


   public AllSubjectAdapter(Activity context){
        this.activity=context;
//        this.itemList = bidoffersellerLists;
//        this.origList = bidoffersellerLists;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public AllSubjectAdapter(Activity activity, FragmentManager fragmentManager, RelativeLayout header) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.header = header;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= mInflater.inflate(R.layout.student_row_exam_subject,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final  int position){

        holder.click_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pushFragment(new AllMaterialChapterFragment(activity, fragmentManager, header, drawer), "Education", true);

                //SelectedExam();
//                Intent i= new Intent(context, AllMaterialChapterctivity.class);
//                context.startActivity(i);
//                context.finish();
            }
        });
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

    @Override
    public long getItemId(int position){
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

     @Override
     public int getItemCount(){
        return 3;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

       LinearLayout click_item;
       ImageView roundedimag;
       TextView textview;
       public ViewHolder(@NonNull View itemView){
        super(itemView);

           click_item=(LinearLayout)itemView.findViewById(R.id.click_item);
           roundedimag=(ImageView) itemView.findViewById(R.id.roundedimag);
           textview=(TextView)itemView.findViewById(R.id.textview);

        }

    }






//    public void pushFragment(Fragment frag, String tag, Boolean addtobackstack) {
////            FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.rl_content, frag, tag);
//        if (addtobackstack) {
//            transaction.addToBackStack(null);
//        }
//        transaction.commit();
//    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                final FilterResults oReturn = new FilterResults();
//                final ArrayList<BidoffersellerList> results = new ArrayList<>();
//                if (origList == null)
//                    origList = new ArrayList<>(itemList);
//                if (constraint != null && constraint.length() > 0) {
//                    if (origList != null && origList.size() > 0) {
//                        for (final BidoffersellerList cd : origList) {
//                            if (cd.getBsProductName().toLowerCase().contains(constraint.toString().toLowerCase())||cd.getBsCompanyName().toLowerCase().contains(constraint.toString().toLowerCase()))
//                                results.add(cd);
//                        }
//                    }
//                    oReturn.values = results;
//                    oReturn.count = results.size();//newly Aded by ZA
//                } else {
//                    oReturn.values = origList;
//                    oReturn.count = origList.size();//newly added by ZA
//                }
//                return oReturn;
//            }
//
//            @SuppressWarnings("unchecked")
//            @Override
//            protected void publishResults(final CharSequence constraint,
//                                          FilterResults results) {
//                itemList = new ArrayList<>((ArrayList<BidoffersellerList>) results.values);
//                // FIXME: 8/16/2017 implement Comparable with sort below
//                ///Collections.sort(itemList);
//                notifyDataSetChanged();
//            }
//        };
//    }

}
