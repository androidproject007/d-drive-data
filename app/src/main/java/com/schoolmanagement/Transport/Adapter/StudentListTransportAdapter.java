package com.schoolmanagement.Transport.Adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;


import com.schoolmanagement.R;
import com.schoolmanagement.Teachers.Interface.OnDataChangeListener;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class StudentListTransportAdapter extends RecyclerView.Adapter<StudentListTransportAdapter.ViewHolder>{

     Activity activity;
     OnDataChangeListener mOnDataChangeListener;
     CharSequence mSearch=null;
     public static final String FILE_PROVIDER_AUTHORITY = "com.history.provider";
     private ArrayList<View> mPrintView = new ArrayList<>();

    public StudentListTransportAdapter(Activity activity) {
        this.activity = activity;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transport_row_studentlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position){

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
       TextView name;
       RadioGroup radiogroup;
       RadioButton present;
       TextView student_name,std,mobile,address;
       Button pickup,absent;

       //LinearLayout click_item;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            student_name=(TextView)itemView.findViewById(R.id.student_name);
            std=(TextView)itemView.findViewById(R.id.std);
            mobile=(TextView)itemView.findViewById(R.id.mobile);
            address=(TextView)itemView.findViewById(R.id.address);
            pickup=(Button)itemView.findViewById(R.id.pickup);
            absent=(Button)itemView.findViewById(R.id.absent);

           // click_item=(LinearLayout)itemView.findViewById(R.id.click_item);

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

    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener){
        mOnDataChangeListener = onDataChangeListener;
    }
}
