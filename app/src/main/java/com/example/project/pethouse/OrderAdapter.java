package com.example.project.pethouse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.pethouse.model.HeaderBooking;

import java.util.ArrayList;
import java.util.List;

        /*
        Edited By William Loo
        Date : 10 Mei 2020
        Purpose : viewholder blabla
        */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    // this adapter contains :
    // 1. ViewHolder
    // your xml viewholder will be represented in java code

    // 2. Method onCreateViewHolder
    // this method will create a viewholder xml

    // 3. Method onBindViewHolder
    // this method will bind your data to viewholder that has been created before

    // 4. Method getItemCount()
    // this method will return the size of the list data

    // 5. Method updateData()
    // update adapter using DiffUtil (not mandatory, you can use notifyDataSetChange() instead, but diffutil is better :))


    private List<HeaderBooking> headerBookingList = new ArrayList<>();
    private OnItemClick listener;

    public OrderAdapter(OnItemClick listener){
        this.listener = listener;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView TextViewBookingDate, TextViewPeriod, TextViewTotalPayment,TextViewPosition;
        LinearLayout ll_custom_layout;

        public OrderViewHolder(View view){
            super(view);
            TextViewBookingDate = view.findViewById(R.id.tv_bookingDatecl);
            TextViewPeriod = view.findViewById(R.id.tv_periodcl);
            TextViewTotalPayment = view.findViewById(R.id.tv_totalPaymentcl);
            TextViewPosition = view.findViewById(R.id.number);
            ll_custom_layout = view.findViewById(R.id.ll_custom_layout);
        }

        public void bind(final HeaderBooking headerBooking, final int position){
            TextViewBookingDate.setText(headerBooking.getBookingDate());
            TextViewPeriod.setText(headerBooking.getPeriod());
            TextViewTotalPayment.setText(headerBooking.getTotalPayment());
            TextViewPosition.setText("" + (position+1));

            ll_custom_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(headerBooking, position);
                }
            });
        }
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 3 parameter : layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout,
                        parent,
                        false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        HeaderBooking headerBooking = headerBookingList.get(position);
        holder.bind(headerBooking, position);
    }

    @Override
    public int getItemCount() {
        return headerBookingList.size();
    }

    public void updateData(List<HeaderBooking> newList){
        MyDiffUtil myDiffUtil = new MyDiffUtil(headerBookingList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(myDiffUtil);
        headerBookingList.clear();
        headerBookingList.addAll(newList);

        result.dispatchUpdatesTo(this);
    }

    public interface OnItemClick{
        void OnClick(HeaderBooking headerBooking, int position);
    }
}
