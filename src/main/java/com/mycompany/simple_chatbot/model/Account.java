/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.model;

/**
 *
 * @author lesan
 */
public class Account {
    private String id;
    private String password;
    private String surname;
    private String middleName;
    private String firstName;
    private String email;
    private String phone;

    private Account() {}
    
    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
 
    public static class Builder {
    
        private String id;
        private String surname;
        private String password;
        private String middleName;
        private String firstName;
        private String email;
        private String phone;

        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
    
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        
        public Account build() {
            Account account = new Account();
            account.id = this.id;
            account.email = this.email;
            account.firstName = this.firstName;
            account.middleName = this.middleName;
            account.surname = this.surname;
            account.phone = this.phone;
            account.password = this.password;
            return account;
        }
        
    }
    
}
