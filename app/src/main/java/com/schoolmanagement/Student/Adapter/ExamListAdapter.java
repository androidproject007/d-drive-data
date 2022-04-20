package com.schoolmanagement.Student.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolmanagement.R;
import com.schoolmanagement.Student.Fragment.ExamResultFragment;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ViewHolder>{

     Dialog more_details;
     Activity activity;
//     List<Attandancelist> item= new ArrayList<>();
//     List<Attandancelist> origList= new ArrayList<>();
    Dialog dialog,dialogedit;
    CharSequence mSearch=null;
    FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public static final String FILE_PROVIDER_AUTHORITY = "com.history.provider";
    private ArrayList<View> mPrintView = new ArrayList<>();
    public ExamListAdapter(Activity activity, FragmentManager fragmentManager, DrawerLayout drawer, RelativeLayout header) {
        this.activity = activity;
        this.fragmentManager=fragmentManager;
        this.drawer=drawer;
        this.header=header;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachers_row_exam_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position){
        holder.click_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new ExamResultFragment(activity, fragmentManager, header, drawer, "1"), "Education", true);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @Override
    public int getItemCount(){
        return 3;
       // return item.size();
    }
    public ArrayList<View> getPrintView() {
        return mPrintView;
    }


    private Uri buildFileProviderUri(@NonNull Uri uri) {
        return FileProvider.getUriForFile(activity,
                FILE_PROVIDER_AUTHORITY,
                new File(uri.getPath()));
    }
    public String getExtension(String fileName) {
        String encoded;
        try {
            encoded = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            encoded = fileName;
        }
        return MimeTypeMap.getFileExtensionFromUrl(encoded).toLowerCase();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       LinearLayout click_item;
       TextView exam_name,topic_name,class_name ;

       public ViewHolder(@NonNull View itemView){
            super(itemView);
            click_item=(LinearLayout)itemView.findViewById(R.id.click_item);
            exam_name=(TextView)itemView.findViewById(R.id.exam_name);
            topic_name=(TextView)itemView.findViewById(R.id.topic_name);
            class_name=(TextView)itemView.findViewById(R.id.class_name);
       }
    }
//    void updateList(int pos){
//        origList.remove(pos);
//    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                final FilterResults oReturn = new FilterResults();
//                final ArrayList<Attandancelist> results = new ArrayList<>();
//                if (origList == null) {
//                    origList = new ArrayList<>();
//                }
//                mSearch=constraint;
//                if (constraint != null && constraint.length() > 0) {
//                    if (origList != null && origList.size() > 0) {
//                        for (final Attandancelist cd : origList) {
//                            if (cd.getName().toLowerCase().contains(constraint.toString().toLowerCase())||cd.getDate().toLowerCase().contains(constraint.toString().toLowerCase()))
//                                results.add(cd);
//                        }
//                    }
//
//                    oReturn.values = results;
//                    oReturn.count = results.size();//newly Aded by ZA
//
//                } else {
//
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
//                item = new ArrayList<>((ArrayList<Attandancelist>) results.values);
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
