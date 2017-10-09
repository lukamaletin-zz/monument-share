package com.example.lukamaletin.monumentshare.utils;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(SharedPref.Scope.UNIQUE)
public interface Preferences {

    int id();
}
