package com.example.servicenovigrad;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListServiceAdapter extends RecyclerView.Adapter<ListServiceAdapter.ViewHolder>{
    private RecyclerView toAddRecycler;
    private ArrayList<ServiceModel> addInfo = new ArrayList<>();
    private Context context;
    private ArrayList<ServiceModel> toAdd;

    public ListServiceAdapter(RecyclerView toAddRecycler, Context context, ArrayList<ServiceModel> toAdd) {
        this.toAddRecycler = toAddRecycler;
        this.context = context;
        this.toAdd = toAdd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_list_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.serviceName.setText("Nom du service  : " +addInfo.get(position).getServiceName());
        holder.servicePrice.setText("Prix du service  : " +addInfo.get(position).getServicePrice());
        holder.count.setText("Service Numero : "+(position+1));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                boolean inside = false;
                if(toAdd.size()==0){
                    toAdd.add(addInfo.get(position));
                }else {
                    for (int i =0;i<toAdd.size();i++) {
                        if (addInfo.get(position).equals(toAdd.get(i))) {
                            inside= true;
                        }

                    }
                    if(!inside){
                        toAdd.add(addInfo.get(position));
                    }
                    else{
                        Toast.makeText(context,"Service deja ajoute", Toast.LENGTH_LONG).show();
                    }
                }


                notifyDataSetChanged();
                PendingBranchService  toAddAdapter = new PendingBranchService(context);
                toAddAdapter.setAddInfo(toAdd);


                toAddRecycler.setAdapter(toAddAdapter);
                toAddRecycler.setLayoutManager(new GridLayoutManager(context,3));


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
        private TextView count, serviceName,servicePrice;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.addCount);
            serviceName = itemView.findViewById(R.id.addName);
            servicePrice = itemView.findViewById(R.id.addPrice);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
