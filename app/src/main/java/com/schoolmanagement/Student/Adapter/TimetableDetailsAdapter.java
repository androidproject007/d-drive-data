package com.schoolmanagement.Student.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.MyProgressDialog;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Model.AttandenceList;

import java.util.List;


public class TimetableDetailsAdapter extends RecyclerView.Adapter<TimetableDetailsAdapter.ViewHolder> {
    public Activity context;
    public FragmentManager fragmentManager;
    LayoutInflater mInflater;
    RelativeLayout header;
    DrawerLayout drawer;
    MyProgressDialog myProgressDialog;
    Dialog dialog;
    List<AttandenceList> attandenceLists;

 //  List<BidoffersellerList>bidoffersellerLists;
//    protected List<BidoffersellerList> itemList;
//    protected List<BidoffersellerList> origList;

    // Disable touch detection for parent recyclerView if we use vertical nested recyclerViews

    public TimetableDetailsAdapter(Activity context){
        this.context=context;
//        this.itemList = bidoffersellerLists;
//        this.origList = bidoffersellerLists;
       // this.bidoffersellerLists=bidoffersellerLists;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= mInflater.inflate(R.layout.student_row_timetable_details,parent,false);



       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final  int position){
//        AttandenceList item= attandenceLists.get(position);
//        holder.date.setText(item.getDate());
//        if(item.getStatus().equals("0")) {
//
//            holder.at_status.setText("Present");
//            holder.at_status.setBackgroundColor(context.getResources().getColor(R.color.btn_light_blue_new));
//
//        }else{
//            holder.at_status.setText("Absent");
//            holder.at_status.setBackgroundColor(context.getResources().getColor(R.color.red_new));
//        }
    }

    @Override
    public long getItemId(int position){
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position){
        return super.getItemViewType(position);
    }

     @Override
     public int getItemCount(){
        return 3;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView subject_name,teachaers_name,time;
        public ViewHolder(@NonNull View itemView){
        super(itemView);

            subject_name=(TextView)itemView.findViewById(R.id.subject_name);
            teachaers_name=(TextView)itemView.findViewById(R.id.teachaers_name);
            time=(TextView)itemView.findViewById(R.id.time);
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
