package com.Finance.Account;

import com.Finance.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private Long ownerId;
    private double amount;
    private Currency currency;
    @CreationTimestamp
    private Timestamp createdDate;
    public Account() {}

    public Account(Long ownerId, double amount, Currency currency) {
        this.ownerId = ownerId;
        this.amount = amount;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
        return Double.compare(amount, account.amount) == 0 && Objects.equals(id, account.id) && Objects.equals(ownerId, account.ownerId) && currency == account.currency && Objects.equals(createdDate, account.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, amount, currency, createdDate);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", value=" + amount +
                ", currency=" + currency +
                ", createdDate=" + createdDate +
                '}';
    }
}
