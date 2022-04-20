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
import com.schoolmanagement.Student.Model.Self_Study_Gks_Object;
import java.util.List;


public class SoultionAppAdapter extends RecyclerView.Adapter<SoultionAppAdapter.ViewHolder> {

    public Activity context;
    public FragmentManager fragmentManager;
    LayoutInflater mInflater;
    RelativeLayout header;
    DrawerLayout drawer;
    MyProgressDialog myProgressDialog;
    Dialog dialog;
    List<Self_Study_Gks_Object> items;

    public SoultionAppAdapter(Activity context, List<Self_Study_Gks_Object> items){
         this.context=context;
         this.items=items;
         mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
      View view= mInflater.inflate(R.layout.row_student_solution,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final  int position) {

        Self_Study_Gks_Object item= items.get(position);
        holder.msgQnsPaperOp1.setText(item.getOp1());
        holder.msgQnsPaperOp2.setText(item.getOp2());
        holder.msgQnsPaperOp3.setText(item.getOp3());
        holder.msgQnsPaperOp4.setText(item.getOp4());
        holder.msgQnsPaperQns.setText(item.getQns());

        if(item.getOp1().equals(item.getRingtans())){
            holder.msgQnsPaperOp1.setTextColor(context.getResources().getColor(R.color.green_color));
            holder.msgQnsPaperOp2.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp3.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp4.setTextColor(context.getResources().getColor(R.color.black));
        }else if(item.getOp2().equals(item.getRingtans())){
            holder.msgQnsPaperOp1.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp2.setTextColor(context.getResources().getColor(R.color.green_color));
            holder.msgQnsPaperOp3.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp4.setTextColor(context.getResources().getColor(R.color.black));
        }else if(item.getOp3().equals(item.getRingtans())){
            holder.msgQnsPaperOp1.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp2.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp3.setTextColor(context.getResources().getColor(R.color.green_color));
            holder.msgQnsPaperOp4.setTextColor(context.getResources().getColor(R.color.black));
        }else if(item.getOp4().equals(item.getRingtans())){
            holder.msgQnsPaperOp1.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp2.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp3.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgQnsPaperOp4.setTextColor(context.getResources().getColor(R.color.green_color));
        }
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
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView msgQnsPaperQns,msgQnsPaperOp1,msgQnsPaperOp2,msgQnsPaperOp3,msgQnsPaperOp4;
      public ViewHolder(@NonNull View itemView){
        super(itemView);
          msgQnsPaperQns=(TextView)itemView.findViewById(R.id.msgQnsPaperQns);
          msgQnsPaperOp1=(TextView)itemView.findViewById(R.id.msgQnsPaperOp1);
          msgQnsPaperOp2=(TextView)itemView.findViewById(R.id.msgQnsPaperOp2);
          msgQnsPaperOp3=(TextView)itemView.findViewById(R.id.msgQnsPaperOp3);
          msgQnsPaperOp4=(TextView)itemView.findViewById(R.id.msgQnsPaperOp4);
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
