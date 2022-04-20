package com.schoolmanagement.Student.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.Other.MyProgressDialog;
import com.schoolmanagement.R;



public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    public Activity context;
    public FragmentManager fragmentManager;
    LayoutInflater mInflater;
    RelativeLayout header;
    DrawerLayout drawer;
    MyProgressDialog myProgressDialog;
    Dialog dialog;

    // Disable touch detection for parent recyclerView if we use vertical nested recyclerViews

    public ChapterAdapter(Activity context){
        this.context=context;
//        this.itemList = bidoffersellerLists;
//        this.origList = bidoffersellerLists;
       // this.bidoffersellerLists=bidoffersellerLists;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= mInflater.inflate(R.layout.student_row_chapter_material,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final  int position){

      holder.exam.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

//              Intent i= new Intent(context, Selection_Exam_Activity.class);
//              i.putExtra("select_subjects", "Maths");
//              i.putExtra("spinner", "25");
//              i.putExtra("sub_code_type","2152");
//           context.startActivity(i);
          }
      });
      holder.pdf.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


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

      TextView date,at_status,chapter_name,topic_name,description;
      ImageView exam,pdf,video;

       public ViewHolder(@NonNull View itemView){
        super(itemView);
          chapter_name=(TextView)itemView.findViewById(R.id.chapter_name);
          topic_name=(TextView)itemView.findViewById(R.id.topic_name);
          description=(TextView)itemView.findViewById(R.id.description);
          date=(TextView)itemView.findViewById(R.id.date);
          at_status=(TextView)itemView.findViewById(R.id.at_status);
          exam=(ImageView)itemView.findViewById(R.id.exam);
          pdf=(ImageView)itemView.findViewById(R.id.pdf);
          video=(ImageView)itemView.findViewById(R.id.video);

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
