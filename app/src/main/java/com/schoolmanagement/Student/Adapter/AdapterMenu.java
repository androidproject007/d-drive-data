package com.schoolmanagement.Student.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.Model.info;
import java.util.ArrayList;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MyViewHolder>{

    private Context context;
    private ArrayList<info> arrayList;
    private int selectedItem=-1;

   public AdapterMenu(Context context, ArrayList<info> list){
        this.context = context;
        this.arrayList = list;
    }

  public int getSelectedItem(){
        return selectedItem;
    }

   public void setSelectedItem(int selectedItem){
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }

    public void updateItem(int index, info item){
        arrayList.set(index, item);
        this.notifyItemChanged(index);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title,tv_right;
        ImageView iv_icon;
        LinearLayout ll_main;

        public MyViewHolder(View itemView){
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
//            tv_right = (TextView) itemView.findViewById(R.id.tv_right);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            ll_main= (LinearLayout) itemView.findViewById(R.id.ll_main);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position){
        info item = arrayList.get(position);
        String title=item.getName();
        //int icon=item.getImage();
        if (position==selectedItem){
            holder.ll_main.setBackgroundColor(context.getResources().getColor(R.color.orange_light));
            holder.tv_title.setTextColor(context.getResources().getColor(R.color.black));
           // holder.iv_icon.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);

        }else {
            holder.ll_main.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.tv_title.setTextColor(context.getResources().getColor(R.color.black));
//            holder.tv_right.setVisibility(View.INVISIBLE);
          //  holder.iv_icon.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        holder.tv_title.setText(title);
        holder.iv_icon.setImageDrawable(item.getImage());
    }

    @Override
    public int getItemCount(){
        return arrayList.size();
    }
}
