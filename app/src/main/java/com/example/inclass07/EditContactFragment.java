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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditContactFragment extends Fragment {


    private static final String CONTACT_TOKEN = "CONTACT_TOKEN";

    private Contact mContact;

    EditText editContactETName, editContactETEmail, editContactETPhone;
    String selection;
    RadioGroup editContactRG;

    public EditContactFragment() {
        // Required empty public constructor
    }

    public static EditContactFragment newInstance(Contact contact) {
        EditContactFragment fragment = new EditContactFragment();
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

        getActivity().setTitle("Edit Contact");

        View view = inflater.inflate(R.layout.fragment_edit_contact, container, false);

        editContactETName = view.findViewById(R.id.editContactETName);
        editContactETEmail = view.findViewById(R.id.editContactETEmail);
        editContactETPhone = view.findViewById(R.id.editContactETPhone);
        editContactRG = view.findViewById(R.id.editContactRG);
        view.findViewById(R.id.editContactBTUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editContactETName.getText().toString();
                String email = editContactETEmail.getText().toString();
                String phone = editContactETPhone.getText().toString();

                int checkId = editContactRG.getCheckedRadioButtonId();
                if (checkId == R.id.editContactRBHome) {
                    selection = "HOME";
                } else if (checkId == R.id.editContactRBCell) {
                    selection = "CELL";
                } else if (checkId == R.id.editContactRBOffice) {
                    selection = "OFFICE";
                }

                if (name.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter name", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_SHORT).show();
                } else if (phone.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter phone", Toast.LENGTH_SHORT).show();
                } else if (selection == null) {
                    Toast.makeText(getActivity(), "Please select type", Toast.LENGTH_SHORT).show();
                } else {
                    mListener.updateContact(mContact.id, editContactETName.getText().toString(), editContactETEmail.getText().toString(), editContactETPhone.getText().toString(), selection);
                }
            }
        });
        return view;
    }

    EditContactFragment.EditContactListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (EditContactFragment.EditContactListener) context;
    }

    interface EditContactListener {
        void updateContact(String id, String name, String email, String phone, String type);
    }
}