package com.example.hienthitinnhanchat_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class messAdapter extends RecyclerView.Adapter<messAdapter.messViewHolder> {
    private List<messenger> messengers;

    public void setdata(List<messenger> list){
        this.messengers=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public messViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messer,parent,false);
        return new messViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull messViewHolder holder, int position) {

        messenger messenger=messengers.get(position);

        if(messenger==null){
            return;
        }
        holder.txtmess.setText(messenger.getMess());
    }

    @Override
    public int getItemCount() {
        if(messengers!=null){
            return messengers.size();
        }
        return 0;
    }

    public class messViewHolder extends RecyclerView.ViewHolder{
        private TextView txtmess;
        public messViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmess=(TextView) itemView.findViewById(R.id.txtmess);
        }
    }
}
