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
import com.schoolmanagement.Student.Fragment.Selection_Exam_Fragment;

import java.util.ArrayList;
import java.util.List;


public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    public Activity context;
    public FragmentManager fragmentManager;
    LayoutInflater mInflater;
    RelativeLayout header;
    DrawerLayout drawer;
    MyProgressDialog myProgressDialog;
    Dialog Selection_Exam;
    TextView select_subject, id_start;
    String  select_subjects;
    Spinner spinner;
    String gkput,marksput,marksvalue,sub_code;

 //  List<BidoffersellerList>bidoffersellerLists;
//    protected List<BidoffersellerList> itemList;
//    protected List<BidoffersellerList> origList;
    // Disable touch detection for parent recyclerView if we use vertical nested recyclerViews

    public ExamAdapter(Activity context, FragmentManager fragmentManager, DrawerLayout drawer, RelativeLayout header){
        this.context=context;
        this.fragmentManager=fragmentManager;
        this.drawer=drawer;
        this.header=header;

//        this.itemList = bidoffersellerLists;
//        this.origList = bidoffersellerLists;
       // this.bidoffersellerLists=bidoffersellerLists;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                SelectedExam();
//                Intent i= new Intent(context, Selection_Exam_Activity.class);
//                context.startActivity(i);
//                context.finish();
            }
        });

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
        LinearLayout click_item;

        public ViewHolder(@NonNull View itemView){
        super(itemView);
            click_item=(LinearLayout)itemView.findViewById(R.id.click_item);
        }

    }


   public void SelectedExam() {
        Selection_Exam = new Dialog(new ContextThemeWrapper(context, R.style.DialogSlideAnim));
        Selection_Exam.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Selection_Exam.setContentView(R.layout.select_exam_dialog);
        Selection_Exam.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Selection_Exam.getWindow().setGravity(Gravity.CENTER);
        Selection_Exam.getWindow()
                .setLayout(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
        select_subject = (TextView) Selection_Exam.findViewById(R.id.select_subject);
     //   select_subject.setText("Maths");
        gkput = select_subject.getText().toString().trim();
        spinner = (Spinner) Selection_Exam.findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Exam");
        categories.add("25 Marks");
        categories.add("50 Marks");
        categories.add("100 Marks");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        id_start = (TextView) Selection_Exam.findViewById(R.id.id_start);
        id_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinner.getSelectedItem().toString().trim().equals("Select Exam")){
                    Toast.makeText(context, "Please Select Exam", Toast.LENGTH_SHORT).show();
                } else if (!spinner.getSelectedItem().toString().trim().equals("")) {
                    marksput = spinner.getSelectedItem().toString().trim();
                    if (marksput.equals("25 Marks")){
                        marksvalue = "25";
                    }else if(marksput.equals("50 Marks")){
                        marksvalue ="50";
                    }else if(marksput.equals("100 Marks")){
                        marksvalue = "100";
                    }
//                    Intent i = new Intent(context, Selection_Exam_Activity.class);
                    marksput = spinner.getSelectedItem().toString().trim();
                   // i.putExtra("select_subjects", gkput);
//                    i.putExtra("spinner", marksvalue);
//                    i.putExtra("sub_code_type","2152");
//                  //  i.putExtra("sub_code_type",sub_code);
//                    context.startActivity(i);
//                    context.finish();
            pushFragment(new Selection_Exam_Fragment(context, fragmentManager, header, drawer,gkput,"2152",marksvalue), "Select Exam", true);
            Selection_Exam.dismiss();

                }
            }
        });

        Selection_Exam.show();
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
