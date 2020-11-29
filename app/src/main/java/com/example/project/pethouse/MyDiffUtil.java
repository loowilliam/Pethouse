package com.example.project.pethouse;

import androidx.recyclerview.widget.DiffUtil;

import com.example.project.pethouse.model.HeaderBooking;

import java.util.List;

        /*
        Edited By William Loo
        Date : 10 Mei 2020
        Purpose : update recyclerview
        */

public class MyDiffUtil extends DiffUtil.Callback{
    // This class will check whether the 2 list have a different item or not
    // this class implements 4 main method :

    // getOldListSize() -> return the old list size
    // getNewListSize() -> return the new list size
    // areItemsTheSame() -> return whether the old list item and new list item is same or not by USING ID
    // areContentsTheSame() -> return whether the old list item and new list item is same or not by USING THE CONTENT
    //                         but you can assume that if the id is same, the contents also same too.

    private List<HeaderBooking> oldList;
    private List<HeaderBooking> newList;

    public MyDiffUtil(List<HeaderBooking> oldList, List<HeaderBooking> newList){
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        int oldId = oldList.get(oldItemPosition).getBookingID();
        int newId = newList.get(newItemPosition).getBookingID();

        return oldId == newId;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int oldId = oldList.get(oldItemPosition).getBookingID();
        int newId = newList.get(newItemPosition).getBookingID();

        return oldId == newId;
    }
}
