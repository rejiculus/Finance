package com.Finance.User;

import com.Finance.Account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Column(name="user_password")
    private String password;

    private Map<Long, Account> accounts;

    public User(){}
    public User(String username, String password){
        this.username = username;
        this.password = password;//todo encrypt
        this.accounts = new TreeMap<>();
    }
    public User(String username, String password, Map<Long, Account> accounts){
        this.username = username;
        this.password = password;//todo encrypt
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Long, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<Long, Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(accounts, user.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, accounts);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
