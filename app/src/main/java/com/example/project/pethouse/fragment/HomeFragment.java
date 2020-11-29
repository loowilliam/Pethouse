package com.example.project.pethouse.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.pethouse.activity.PethouseProfileActivity;
import com.example.project.pethouse.activity.PromoActivity;
import com.example.project.pethouse.R;
import com.example.project.pethouse.model.HeaderBooking;
import com.example.project.pethouse.repository.HeaderBookingRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
Edited By: Eric
Last Edited: 22:57 23 April 2020
Purpose: Memasukkan Spinner done, Date picker done, Add Event Click Listener ke Book Activity
 */

/*
Edited By: Eric
Last Edited: 18:37 25 April 2020
Purpose: Ingin mengubah imageView
 */
/*
Edited By: Eric
Last Edited: 20:31 09 May 2020
Purpose: Menambahkan clicklistener pada Term Spinner dan Location Spinner, dan validasi pada saat searching
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner DropDownTerm;
    private Spinner DropDownLocation;
    private Button ButtonSearch;
    private DatePicker DatePicker;
    private Calendar Calendar;
    private int year, month, day;
    private TextView TextViewCheckIn, TextViewCheckOut, TextViewCheckOutTitle;
    private ImageView ImageViewCard, ImageViewLogo;
    private HeaderBookingRepository headerBookingRepository;
    //PositionTerm itu adalah posisi type Term
    private int PositionTerm = 0, LocationIndex = -1;
    //PositionLocation itu untuk mengetahui Index dari Lokasi yang dipilih
    private String PositionLocation = "None";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //ini untuk memperoleh id dari spinner (select yang ada di depan)
        DropDownTerm = (Spinner) view.findViewById(R.id.spinner_term);
        DropDownLocation = (Spinner) view.findViewById(R.id.spinner_location);
        TextViewCheckIn = (TextView) view.findViewById(R.id.tv_checkindate);
        TextViewCheckOut = (TextView) view.findViewById(R.id.tv_checkoutdate);
        ButtonSearch = (Button) view.findViewById(R.id.btn_search);
        ImageViewCard = (ImageView) view.findViewById(R.id.iv_card);
        ImageViewLogo = (ImageView) view.findViewById(R.id.iv_logo);
        TextViewCheckOutTitle = (TextView) view.findViewById(R.id.tv_checkout);

        //Melakukan set pada gambar
        ImageViewLogo.setImageResource(R.drawable.logo_pethouse);
        ImageViewCard.setImageResource(R.drawable.banner_pengguna_baru);
        //
        //menampilkan apa saja yang ada di dalam period dan location
        String[] itemsTerm = new String[]{"Choose your term type","Long Term", "Short Term"};
        String[] itemsLocation = new String[]{
                "Choose your location",
                "Jakarta Barat",
                "Jakarta Utara" ,
                "Jakarta Selatan",
                "Jakarta Timur",
                "Jakarta Pusat"};
        //memasukkan kedalam adapter, yang nantinya ditambahkan ke dropdown
        ArrayAdapter<String> adapterTerm = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsTerm);
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsLocation);

        //melakukan set pada masing - masing drop down agar muncul di UI
        DropDownTerm.setAdapter(adapterTerm);
        DropDownLocation.setAdapter(adapterLocation);
        //Create setOnClick to Spinner Period
        DropDownTerm.setOnItemSelectedListener(this);
        DropDownLocation.setOnItemSelectedListener(this);

        setOnClickListener();

        return view;
    }

    private void setOnClickListener(){
        //Purpose: ingin melakukan set  dan menyimpan tanggal hari ini
        Calendar = Calendar.getInstance();
        year = Calendar.get(Calendar.YEAR);
        month = Calendar.get(Calendar.MONTH);
        day = Calendar.get(Calendar.DAY_OF_MONTH);

        //Purpose: ingin membuat datepicker untuk checkin
        TextViewCheckIn.setOnClickListener(new View.OnClickListener() {
            //getActivity() = memperoleh activity yang ada di fragment ini
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date =String.format("%02d/%02d/%d", dayOfMonth,month, year);
                        TextViewCheckIn.setText(date);

                    }
                },year,month,day);
                long today = Calendar.getTimeInMillis();
                datePickerDialog.getDatePicker().setMinDate(today);
                datePickerDialog.show();
            }
        });

        //Purpose: ingin membuat datepicker untuk checkout
        TextViewCheckOut.setOnClickListener(new View.OnClickListener() {
            //getActivity() = memperoleh activity yang ada di fragment ini
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date =String.format("%02d/%02d/%d", dayOfMonth,month, year);
                        TextViewCheckOut.setText(date);
                        long today = Calendar.getTimeInMillis();
                        view.setMinDate(today);
                    }
                },year,month,day);
                long today = Calendar.getTimeInMillis();
                datePickerDialog.getDatePicker().setMinDate(today);
                //memunculkan pilihan pada UI
                datePickerDialog.show();
            }
        });
        //nantinya mungkin diperlukan supaya pada saat user menekan submit, dropdown yang dipilih akan tersave
        ButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid = true;

                //Step 1: Check Position Term dipilih atau tidak

                //1 artinya Long Term
                //2 artinya Short Term
                if(PositionTerm == 0)
                    isValid = false;
                else if(PositionTerm == 1){
                    //Step 2: Validasi Check Date dulu
                    boolean isDate = false;
                    String checkin = TextViewCheckIn.getText().toString().trim();
                    String checkout = TextViewCheckOut.getText().toString().trim();
                    isDate = CheckDate(checkin, checkout);
                    if(!isDate){
                        isValid = false;
                    }
                }

                //cek kosong buat posisi saat ini
                if(PositionLocation.equals("None")){
                    isValid = false;
                }


                if(isValid == true){
                    //Disini baru input semua data ke dalam intent selanjutnya
                    HeaderBooking headerBooking = new HeaderBooking();
                    String checkin, checkout, location;
                    checkin = TextViewCheckIn.getText().toString().trim();
                    checkout = TextViewCheckOut.getText().toString().trim();
                    location = PositionLocation;

                    headerBookingRepository = new HeaderBookingRepository(getActivity());

                    //kalau 1 masukin CheckOut juga, kalau 2 masukin Checkin aja
                    if(PositionTerm == 1){
                        headerBooking.setCheckIn(checkin);
                        headerBooking.setCheckOut(checkout);
                        headerBooking.setHotelID(LocationIndex);
                        headerBooking.setPeriod("Long Term");

                    }else if(PositionTerm == 2){
                        headerBooking.setCheckIn(checkin);
                        headerBooking.setHotelID(LocationIndex);
                        headerBooking.setPeriod("Short Term");
                    }


                    //perpindahan screen ke BookActivity
                    //Kalau bisa, bawa atribut dari sini yang sudah masuk nanti, untuk dipakai memasukkan data
                    Intent intent = new Intent(getActivity(), PethouseProfileActivity.class);
                    intent.putExtra("HeaderBooking", headerBooking);
                    //bisa menambahkan putExtra, syarat, class ini harus impelements atau extends Serializable
                    startActivity(intent);
                }
            }
        });
        ImageViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PromoActivity.class));
            }
        });
    }

    //checkDate
    private boolean CheckDate(String checkin, String checkout){

        Calendar = Calendar.getInstance();
        year = Calendar.get(Calendar.YEAR);
        month = Calendar.get(Calendar.MONTH);
        day = Calendar.get(Calendar.DAY_OF_MONTH);
        try {
            Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse(checkin);
            Date date2 = new SimpleDateFormat("dd/mm/yyyy").parse(checkout);
            if(date1.before(date2)){
                return true;
            }
        }catch(Exception e){
            Toast.makeText(getActivity(), "Oops! Something's wrong", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0){
            String command = parent.getItemAtPosition(position).toString();
            //if command = Long Term, Show TextView CheckOut
            if(command.equals("Long Term")){
                if(TextViewCheckOutTitle.getVisibility() == View.INVISIBLE){
                    TextViewCheckOutTitle.setVisibility(View.VISIBLE);
                    TextViewCheckOut.setVisibility(View.VISIBLE);
                }
                PositionTerm = 1;
            }else if(command.equals("Short Term")){
                if(TextViewCheckOutTitle.getVisibility() == View.VISIBLE){
                    TextViewCheckOutTitle.setVisibility(View.INVISIBLE);
                    TextViewCheckOut.setVisibility(View.INVISIBLE);
                }
                PositionTerm = 2;
            }else{
                PositionLocation = command;
                LocationIndex = position;

            }


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
