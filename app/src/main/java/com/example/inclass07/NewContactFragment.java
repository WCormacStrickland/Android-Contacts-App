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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class NewContactFragment extends Fragment {

    EditText newContactETName, newContactETEmail, newContactETPhone;
    RadioGroup newContactRG;
    String selection;
//    private Button newContactBTCancel, newContactBTSubmit;

    public NewContactFragment() {
        // Required empty public constructor
    }

    public static NewContactFragment newInstance() {
        NewContactFragment fragment = new NewContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("New Contact");

        View view = inflater.inflate(R.layout.fragment_new_contact, container, false);

        newContactETName = view.findViewById(R.id.newContactETName);
        newContactETEmail = view.findViewById(R.id.newContactETEmail);
        newContactETPhone = view.findViewById(R.id.newContactETPhone);
        newContactRG = view.findViewById(R.id.newContactRG);

        view.findViewById(R.id.newContactBTSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkId = newContactRG.getCheckedRadioButtonId();
                if (checkId == R.id.newContactRBHome) {
                    selection = "HOME";
                } else if (checkId == R.id.newContactRBCell) {
                    selection = "CELL";
                } else if (checkId == R.id.newContactRBOffice) {
                    selection = "OFFICE";
                }

                if(newContactETName.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter name", Toast.LENGTH_SHORT).show();
                } else if(newContactETEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_SHORT).show();
                } else if(newContactETPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                } else if(newContactETPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                } else if (selection == null) {
                    Toast.makeText(getActivity(), "Please select phone type", Toast.LENGTH_SHORT).show();
                } else {
                    mListener.createContact(newContactETName.getText().toString(), newContactETEmail.getText().toString(), newContactETPhone.getText().toString(), selection);
                }
            }
        });

        view.findViewById(R.id.newContactBTCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToContactsList();
            }
        });

        return view;
    }

    NewContactFragment.NewContactListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (NewContactFragment.NewContactListener) context;
    }

    interface NewContactListener {
        void createContact(String name, String email, String phone, String type);
        void goToContactsList();
    }
}