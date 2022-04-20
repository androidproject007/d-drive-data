package com.schoolmanagement.Student.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.MyProgressDialog;
import com.schoolmanagement.R;


import com.schoolmanagement.Student.Model.AttandenceList;

import java.util.List;


public class OnlineStudyAdapter extends RecyclerView.Adapter<OnlineStudyAdapter.ViewHolder> {

    public Activity activity;
    public FragmentManager fragmentManager;
    LayoutInflater mInflater;
    RelativeLayout header;
    DrawerLayout drawer;
    MyProgressDialog myProgressDialog;
    Dialog dialog;
    List<AttandenceList> attandenceLists;


 //List<BidoffersellerList>bidoffersellerLists;
// protected List<BidoffersellerList> itemList;
// protected List<BidoffersellerList> origList;
 // Disable touch detection for parent recyclerView if we use vertical nested recyclerViews

    public OnlineStudyAdapter(Activity activity, FragmentManager fragmentManager, DrawerLayout drawer, RelativeLayout header){
        this.activity=activity;
        this.fragmentManager=fragmentManager;
        this.drawer=drawer;
        this.header=header;
//        this.itemList = bidoffersellerLists;
//        this.origList = bidoffersellerLists;
       // this.bidoffersellerLists=bidoffersellerLists;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= mInflater.inflate(R.layout.student_row_onlinestudy,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final  int position){

     holder.join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // pushFragment(new Online_ZoomFragment(activity, fragmentManager, header, drawer, "0"), "Education", true);

//                Intent i= new Intent(context, Online_ZoomActivity.class);
//                context.startActivity(i);
               // context.finish();

            }
        });



    }

    @Override
    public long getItemId(int position) {
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
        CardView click_item;
        Button join;
        TextView subject_name,time;

      public ViewHolder(@NonNull View itemView){
        super(itemView);

           subject_name=(TextView)itemView.findViewById(R.id.subject_name);
           time= (TextView)itemView.findViewById(R.id.time);
           click_item=(CardView) itemView.findViewById(R.id.click_item);
           join=(Button) itemView.findViewById(R.id.join);

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
