package com.example.go_chat_android;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.go_chat_android.api.ContactAPI;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private ContactDao dao;
    private PostListData postListData;
    private ContactAPI api;

    public ContactsRepository() {
        LocalDatabase db = LocalDatabase.getInstance();
        dao = db.contactDao();
        postListData = new PostListData();
        api = new ContactAPI(contactListData, dao);
    }

    class PostListData extends MutableLiveData<List<Contact>> {
        public PostListData() {
            super();
            setValue(new LinkedList<Contact>());
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() ->
            {
                postListData.postValue(dao.get());
            }).start();

        }
    }

    public LiveData<List<Contact>> getAll() {
        return postListData;
    }

    public void add(final Contact post) {
        api.add(post);
    }

    public void delete(final Contact post) {
        api.delete(post);
    }

    public void reload() {
        api.get();
    }
}

public class GetContactsTask extends AsyncTask<Void, Void, Void> {
    private MutableLiveData<List<Contact>> postListData;
    private ContactDao dao;

    public GetContactsTask(MutableLiveData<List<Contact>> postListData, ContactDao dao) {
        this.postListData = postListData;
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(Void... x) {

        // connect to web-service
        // retrieve posts
        // convert json response to objects
        // update objects in LiveData

        return null;
    }
}