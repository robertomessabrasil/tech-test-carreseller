package com.robertomessabrasil.carreseller.domain.entity.store;

import io.github.robertomessabrasil.jwatch.observer.EventObserver;

public class CustomerEntity {
    private String name;
    private String email;
    private String phone;

    public String getName() {
        return name;
    }

    public CustomerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void validate(EventObserver eventObserver) {
        // to do
    }
}

