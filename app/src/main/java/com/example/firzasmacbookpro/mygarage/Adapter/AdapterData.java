package com.example.firzasmacbookpro.mygarage.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firzasmacbookpro.mygarage.AdminVehicleInsertActivity;
import com.example.firzasmacbookpro.mygarage.Model.ModelData;
import com.example.firzasmacbookpro.mygarage.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterData (Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvid.setText(md.getId());
        holder.tvmodel.setText(md.getModel());

        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvid, tvmodel;
        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvid = (TextView)view.findViewById(R.id.id);
            tvmodel = (TextView)view.findViewById(R.id.model);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent update = new Intent(context, AdminVehicleInsertActivity.class);
                    update.putExtra("update",1);
                    update.putExtra("id",md.getId());
                    update.putExtra("model",md.getModel());
                    update.putExtra("make",md.getMake());
                    update.putExtra("category",md.getCategory());
                    update.putExtra("dateMake",md.getDatemake());

                    context.startActivity(update);

                }
            });
        }
    }
}
