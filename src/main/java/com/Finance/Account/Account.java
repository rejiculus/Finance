package com.Finance.Account;

import com.Finance.Currency;
import com.Finance.User.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User bankUser;
    private double amount;
    private Currency currency;
    @CreationTimestamp
    private Timestamp createdDate;
    public Account() {}

    public Account(User bankUser, double amount, Currency currency) {
        this.bankUser = bankUser;
        this.amount = amount;
        this.currency = currency;
    }
    public Account(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBankUser() {
        return bankUser;
    }

    public void setBankUser(User bankUser) {
        this.bankUser = bankUser;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public double getAmountUSD(){
        return amount * currency.getRate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(amount, account.amount) == 0 && Objects.equals(id, account.id) && Objects.equals(bankUser, account.bankUser) && currency == account.currency && Objects.equals(createdDate, account.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankUser, amount, currency, createdDate);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", bankUserId=" + bankUser.getId() +
                ", amount=" + amount +
                ", currency=" + currency +
                ", createdDate=" + createdDate +
                '}';
    }
}
