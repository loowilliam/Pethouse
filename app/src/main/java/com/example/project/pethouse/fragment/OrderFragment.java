package com.example.project.pethouse.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.pethouse.OrderAdapter;
import com.example.project.pethouse.R;
import com.example.project.pethouse.SharedPreference.SharedPref;
import com.example.project.pethouse.activity.TransactionStatusActivity;
import com.example.project.pethouse.model.HeaderBooking;
import com.example.project.pethouse.model.MsUser;
import com.example.project.pethouse.repository.HeaderBookingRepository;

import java.util.List;

public class OrderFragment extends Fragment {

    /*
    Edited By: Eric
    Date: 26 April 2020
    Purpose: Cuma menambahkan agar box bisa ditekan
     */

        /*
    Edited By: Wilo
    Date: 10 Mei 2020
    Purpose: add recyler view
     */


    private ImageView ImageViewBox;

    private OrderAdapter adapter;
    private List<HeaderBooking> headerBookingList;
    HeaderBookingRepository headerBookingRepository;
    private RecyclerView recyclerView;
    private int counter = 10;
    private SharedPref pref;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        // if you sick of using findViewById, you can search for 'DataBinding'
        // DataBinding is really helpful for your productivity :)
        recyclerView = view.findViewById(R.id.recycler_view);

        setData();
        setAdapter();
        return view;
    }

    public void setData(){
//        // Set data in barbaric way
//        headerBookingList = new ArrayList<>();
//
//        for(int i=0; i<counter; i++){
//            HeaderBooking headerBooking = new HeaderBooking();
//            headerBooking.setBookingDate(Integer.toString(i));
//            headerBooking.setPeriod(Integer.toString(i));
//            headerBooking.setTotalPayment(Integer.toString(i));
//
//            headerBookingList.add(headerBooking);
//        }

        pref = new SharedPref(getActivity());
        SharedPref sharedPref = new SharedPref(getActivity());
        final MsUser msUser = sharedPref.loadUser();

        headerBookingRepository = new HeaderBookingRepository(getActivity());
        headerBookingList = headerBookingRepository.getHeaderBookingByID(msUser.getEmail());
    }

    public void setAdapter(){
        adapter = new OrderAdapter(new OrderAdapter.OnItemClick() {
            @Override
            public void OnClick(HeaderBooking headerBooking, int position) {
//                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
                System.out.println("HotelID: " + headerBooking.getHotelID() + " PetID: " + headerBooking.getPetID());
                Intent intent = new Intent(getActivity(), TransactionStatusActivity.class/*Nama activity target*/);
                intent.putExtra("HeaderBooking", headerBooking);
                intent.putExtra("BookingDate", headerBooking.getBookingDate());
                intent.putExtra("Period", headerBooking.getPeriod());
                intent.putExtra("TotalPayment", headerBooking.getTotalPayment());

                startActivity(intent);

            }
        });
        adapter.updateData(headerBookingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
