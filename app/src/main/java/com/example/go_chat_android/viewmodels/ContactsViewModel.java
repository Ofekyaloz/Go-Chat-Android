package com.example.go_chat_android.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.go_chat_android.Contact;
import com.example.go_chat_android.ContactsRepository;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    private ContactsRepository repository;

    private LiveData<List<Contact>> posts;

    public ContactsViewModel() {
        repository = new ContactsRepository();
        posts = repository.getAll();
    }

    public LiveData<List<Contact>> get() {
        return posts;
    }

    public void add(Contact contact) {
       // repository.add(contact);
    }

    public void delete(Contact contact) {
       // repository.delete(contact);
    }

    public void reload() {
        repository.reload();
    }
}