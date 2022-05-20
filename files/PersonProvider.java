package com.example.demo;


import com.rollbar.notifier.provider.Provider;
import com.rollbar.api.payload.data.Person;

class PersonProvider implements Provider<Person> {

    @Override
    public Person provide() {

        // set this to the current user
        return new Person.Builder()
            .id("10098")
            .build();
    }
}