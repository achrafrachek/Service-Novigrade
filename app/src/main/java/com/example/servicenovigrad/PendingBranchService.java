package com.example.servicenovigrad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PendingBranchService extends RecyclerView.Adapter<PendingBranchService.ViewHolder>{
    private ArrayList<ServiceModel> addInfo = new ArrayList<>();
    private Context context;

    public PendingBranchService(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_list_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtInfoName.setText(addInfo.get(position).getServiceName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo.remove(position);
                notifyDataSetChanged();


            }
        });
    }




    @Override
    public int getItemCount() {
        return addInfo.size();
    }

    public void setAddInfo(ArrayList<ServiceModel> addInfo) {
        this.addInfo = addInfo;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtInfoName;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInfoName = itemView.findViewById(R.id.txtInfoName);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
