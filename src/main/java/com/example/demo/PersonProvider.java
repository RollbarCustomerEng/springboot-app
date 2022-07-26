package com.example.demo;


import com.rollbar.notifier.provider.Provider;
import com.rollbar.api.payload.data.Person;

class PersonProvider implements Provider<Person> {

    @Override
    public Person provide() {


        int personId = 10000 + (int)(Math.random() * ((20000 - 10000) + 1));

        // set this to the current user
        return new Person.Builder()
            .id(String.valueOf((personId)))
            .build();
    }
}