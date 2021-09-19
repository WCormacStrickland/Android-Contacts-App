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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ContactsListFragment extends Fragment {

    ArrayList<Contact> contacts = new ArrayList<>();

    LinearLayoutManager layoutManager;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    private final OkHttpClient client = new OkHttpClient();

    public ContactsListFragment() {
        // Required empty public constructor
    }

    public static ContactsListFragment newInstance() {
        ContactsListFragment fragment = new ContactsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Contacts List");

        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        getContacts();

        recyclerView = view.findViewById(R.id.ContactsListRV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(mListener, contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


//        adapter.notifyDataSetChanged();


        view.findViewById(R.id.ContactsListBTAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToNewContact();
            }
        });

        return view;
    }

    void getContacts() {
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contacts")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    String body = response.body().string();
                    for (String line : body.split(System.getProperty("line.separator"))) {
                        contacts.add(new Contact(line.split(",")[0], line.split(",")[1], line.split(",")[2], line.split(",")[3], line.split(",")[4]));
                        Log.d("penis1", "onCreateViewHolder: " + contacts.get(0).name + contacts.get(0).email + contacts.get(0).phone);
                    }
                }
            }
        });
    }

    ContactsListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ContactsListListener) context;
    }

    interface ContactsListListener {
        void goToContactDetails(Contact contact);
        void goToNewContact();
        void deleteContact(String id);
    }
}