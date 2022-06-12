package com.example.go_chat_android.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.go_chat_android.Contact;

import java.util.List;

public class SampleViewModel extends ViewModel {

    private MutableLiveData<List<Contact>> contacts;

    public MutableLiveData<List<Contact>> getcontacts() {
        if (contacts == null) {
            contacts = new MutableLiveData<>();
        }
        return contacts;
    }
}
