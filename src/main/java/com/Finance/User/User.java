package com.Finance.User;

import com.Finance.Account.Account;
import com.Finance.Currency;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "bank_users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Column(name="user_password")
    private String password;

    @OneToMany(mappedBy = "bankUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    public User(){}
    public User(String username, String password){
        this.username = username;
        this.password = password;//todo encrypt
    }
    public User(String username, String password, List<Account> accounts){
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    public void addAccount(Account account){
        account.setBankUser(this);
        this.accounts.add(account);
    }
    public void removeAccount(Account account){
        this.accounts.remove(account);
        account.setBankUser(null);
    }
    public Account createAccount(double amount, Currency currency){
        Account account = new Account(this, amount, currency);
        this.accounts.add(account);
        return account;
    }
    public String showAccountsId(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i=0;i<accounts.size();i++){
            sb.append(accounts.get(i).getId());
            if(i!=accounts.size()-1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
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
                ", accounts=" + "i will do it later" +
                '}';
    }
}
