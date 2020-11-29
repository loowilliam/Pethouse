package com.example.project.pethouse.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.pethouse.SharedPreference.SharedPref;
import com.example.project.pethouse.activity.AboutUsActivity;
import com.example.project.pethouse.activity.EditActivity;
import com.example.project.pethouse.R;
import com.example.project.pethouse.activity.SplashActivity;
import com.example.project.pethouse.activity.StartActivity;
import com.example.project.pethouse.model.MsUser;

public class AccountFragment extends Fragment {
    private Button ButtonEditProfile, ButtonLogout;
    private TextView TextViewAboutUs, TextViewName, TextViewEmail, TextViewPhone, TextViewPassword;

    /*
    Edited By: Eric
    Date: 01 May 2020
    Purpose: Add Listener to edit profile button
     */
    /*
    Updated By: Eric
    Date: 09 May 2020
    Purpose: Update Profile, masukin Data dari database
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        SharedPref SharedPref = new SharedPref(getActivity());
        MsUser MsUser = SharedPref.loadUser();
        ButtonEditProfile = view.findViewById(R.id.btnEditProfile);
        TextViewAboutUs = view.findViewById(R.id.tv_aboutus);
        TextViewName = view.findViewById(R.id.tv_namevalue);
        TextViewEmail = view.findViewById(R.id.tv_emailvalue);
        TextViewPhone = view.findViewById(R.id.tv_phonevalue);
        TextViewPassword = view.findViewById(R.id.tv_passwordvalue);
        ButtonLogout = view.findViewById(R.id.btnLogout);
        setProfileCurrentUser(MsUser);
        setListener();

        return view;
    }

    //function untuk mengeset value dari TextView
    private void setProfileCurrentUser(MsUser MsUser){
        TextViewName.setText(MsUser.getUserName());
        TextViewEmail.setText(MsUser.getEmail());

        TextViewPhone.setText(MsUser.getPhone());
        TextViewPassword.setText(MsUser.getPassword());
    }

    private void setListener(){
        ButtonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start activity
                startActivity(new Intent(getActivity(), EditActivity.class));
            }
        });
        TextViewAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
            }
        });
        ButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref SharedPref = new SharedPref(getActivity());
                SharedPref.clear();
                startActivity(new Intent(getActivity(), SplashActivity.class));
                getActivity().finish();
            }
        });
    }
}
