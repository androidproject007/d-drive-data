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
import com.schoolmanagement.Student.Fragment.ExamResultStudentFragment;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class ExamResultAdapter extends RecyclerView.Adapter<ExamResultAdapter.ViewHolder>  {

     Activity activity;
     Dialog dialog,dialogedit;
     public static final String FILE_PROVIDER_AUTHORITY = "com.history.provider";
     private ArrayList<View> mPrintView = new ArrayList<>();
     FragmentManager fragmentManager;
     RelativeLayout header;
     DrawerLayout drawer;

    public ExamResultAdapter(Activity activity, FragmentManager fragmentManager, RelativeLayout header, DrawerLayout drawer) {
        this.activity = activity;
        this.fragmentManager= fragmentManager;
        this.header=header;
        this.drawer=drawer;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachares_row_exam_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position){
        holder.click_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new ExamResultStudentFragment(activity, fragmentManager, header, drawer), "Education", true);

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
       TextView name;
       LinearLayout click_item;
       TextView subject_name,topic_name,text_exam,date,percentage;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            click_item=(LinearLayout)itemView.findViewById(R.id.click_item);
            subject_name=(TextView)itemView.findViewById(R.id.subject_name);
            topic_name=(TextView)itemView.findViewById(R.id.topic_name);
            text_exam=(TextView)itemView.findViewById(R.id.text_exam);
            date=(TextView)itemView.findViewById(R.id.date);
            percentage=(TextView)itemView.findViewById(R.id.percentage);
        }
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
