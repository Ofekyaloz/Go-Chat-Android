package com.example.go_chat_android;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.go_chat_android.api.APIService;
import com.example.go_chat_android.entities.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private ContactDao dao;
    private ContactListData contactListData;
    private APIService api;

    public ContactsRepository() {
        //AppDB_Impl db = new AppDB_Impl();
        //dao = db.contactDao();
        contactListData = new ContactListData();
        //api = new ContactAPI(contactListData, dao);
    }

    class ContactListData extends MutableLiveData<List<Contact>> {
        public ContactListData() {
            super();
            setValue(new LinkedList<Contact>());
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() ->
            {
                //contactListData.contactValue(dao.get());
            }).start();

        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add(final Contact contact) {
        //api.add(contact);
    }

    public void delete(final Contact contact) {
        //api.delete(contact);
    }

    public void reload() {
        api.get();
    }
}

class GetContactsTask extends AsyncTask<Void, Void, Void> {
    private MutableLiveData<List<Contact>> contactListData;
    private ContactDao dao;

    public GetContactsTask(MutableLiveData<List<Contact>> contactListData, ContactDao dao) {
        this.contactListData = contactListData;
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(Void... x) {

        // connect to web-service
        // retrieve contactListData
        // convert json response to objects
        // update objects in LiveData

        return null;
    }
}