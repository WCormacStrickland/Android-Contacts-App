/*
Assignment InClass07
InClass07.zip
Chase Scallion and Cormac Strickland
 */


package com.example.inclass07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements ContactsListFragment.ContactsListListener, NewContactFragment.NewContactListener, ContactDetailsFragment.ContactDetailsListener, EditContactFragment.EditContactListener {
    private final OkHttpClient client = new OkHttpClient();
    Contact mContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new ContactsListFragment(), "contactList")
                .commit();
    }

    @Override
    public void goToContactsList() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ContactsListFragment.newInstance(), "contactList")
                .addToBackStack("contactList")
                .commit();
    }

    @Override
    public void goToContactDetails(Contact contact) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ContactDetailsFragment.newInstance(contact), "contactDetails")
                .addToBackStack("contactList")
                .commit();
    }

    @Override
    public void goToNewContact() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, NewContactFragment.newInstance(), "newContact")
                .addToBackStack("contactList")
                .commit();
    }

    @Override
    public void deleteContact(String idToDelete) {
        RequestBody formBody = new FormBody.Builder()
                .add("id", idToDelete)
                .build();

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contact/delete")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.rootView, ContactsListFragment.newInstance(), "contactDetails")
                            .commit();
                }
            }
        });
    }

    @Override
    public void createContact(String name, String email, String phone, String type) {
        RequestBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("email", email)
                .add("phone", phone)
                .add("type", type)
                .build();

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contact/create")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.rootView, ContactsListFragment.newInstance(), "contactList")
                            .commit();
                }
            }
        });
    }


    @Override
    public void goToEditContact(Contact contact) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, EditContactFragment.newInstance(contact), "editContact")
                .addToBackStack("editContact")
                .commit();
    }

    @Override
    public void updateContact(String id, String name, String email, String phone, String type) {
        RequestBody formBody = new FormBody.Builder()
                .add("id", id)
                .add("name", name)
                .add("email", email)
                .add("phone", phone)
                .add("type", type)
                .build();

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contact/update")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.rootView, ContactsListFragment.newInstance(), "contactList")
                            .commit();
                }
            }
        });
    }
}