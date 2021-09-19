/*
Assignment InClass07
InClass07.zip
Chase Scallion and Cormac Strickland
 */

package com.example.inclass07;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder>{
    ContactsListFragment.ContactsListListener mListener;
    ArrayList<Contact> contacts;

    public RecyclerViewAdapter(ContactsListFragment.ContactsListListener mListener, ArrayList<Contact> contacts) {
        this.mListener = mListener;
        this.contacts = contacts;
//        Log.d("penis3", "onCreateViewHolder: " + this.contacts.get(0).name);

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.UserViewHolder holder, int position) {
        holder.mListener = this.mListener;
        holder.contact = contacts.get(position);
        holder.contactRowTVName.setText(contacts.get(position).name);
        holder.contactRowTVEmail.setText(contacts.get(position).email);
        holder.contactRowTVPhone.setText(contacts.get(position).phone);
        holder.contactRowTVType.setText(contacts.get(position).type);

    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {
        ContactsListFragment.ContactsListListener mListener;
        TextView contactRowTVName, contactRowTVEmail, contactRowTVPhone, contactRowTVType;
        Contact contact;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            contactRowTVName = itemView.findViewById(R.id.contactRowTVName);
            contactRowTVEmail = itemView.findViewById(R.id.contactRowTVEmail);
            contactRowTVPhone = itemView.findViewById(R.id.contactRowTVPhone);
            contactRowTVType = itemView.findViewById(R.id.contactRowTVType);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.goToContactDetails(contact);
                }
            });

            itemView.findViewById(R.id.contactRowBTDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.deleteContact(contact.id);
                }
            });
        }
    }
}
