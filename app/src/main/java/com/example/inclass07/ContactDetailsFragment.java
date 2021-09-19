/*
Assignment InClass07
InClass07.zip
Chase Scallion and Cormac Strickland
 */

package com.example.inclass07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactDetailsFragment extends Fragment {

    TextView contactDetailsTVName, contactDetailsTVEmail, contactDetailsTVPhone, contactDetailsTVType;

    private static final String CONTACT_TOKEN = "CONTACT_TOKEN";
    Contact mContact;

    public ContactDetailsFragment() {
        // Required empty public constructor
    }


    public static ContactDetailsFragment newInstance(Contact contact) {
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(CONTACT_TOKEN, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = (Contact)getArguments().getSerializable(CONTACT_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Contact Details");

        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);

        contactDetailsTVName = view.findViewById(R.id.contactDetailsTVName);
        contactDetailsTVEmail = view.findViewById(R.id.contactDetailsTVEmail);
        contactDetailsTVPhone = view.findViewById(R.id.contactDetailsTVPhone);
        contactDetailsTVType = view.findViewById(R.id.contactDetailsTVType);

        contactDetailsTVName.setText(mContact.name);
        contactDetailsTVEmail.setText(mContact.email);
        contactDetailsTVPhone.setText(mContact.phone);
        contactDetailsTVType.setText(mContact.type);

        view.findViewById(R.id.contactDetailsBTDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.deleteContact(mContact.id);
            }
        });
        view.findViewById(R.id.contactDetailsBTUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToEditContact(mContact);
            }
        });
        return view;
    }

    ContactDetailsFragment.ContactDetailsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ContactDetailsFragment.ContactDetailsListener) context;
    }

    interface ContactDetailsListener {
        void deleteContact(String id);
        void goToEditContact(Contact contact);
    }
}