package com.example.firzasmacbookpro.mygarage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


// this class is just for testing recycler in fragment
public class ListMapping extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.maintenance_list_item, viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);

    }

    @Override
    public int getItemCount() {
        return TestData.maintenance.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mListItem;
        private TextView mListReminder;

        public ListViewHolder(View itemView){
            super(itemView);
            mListItem = (TextView)itemView.findViewById(R.id.maintListItem);
            mListReminder = (TextView)itemView.findViewById(R.id.maintListReminder);
            itemView.setOnClickListener(this);
        }

        public void bindView (int position){
            mListItem.setText(TestData.maintenance[position]);
            mListReminder.setText(TestData.reminder[position]);
        }

        public void onClick(View view){

        }

    }
}
