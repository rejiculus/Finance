package com.Finance.Account;

import com.Finance.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class AccountDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("ownerId")
    private Long bankUserId;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("createdDate")
    private Timestamp createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankUserId() {
        return bankUserId;
    }

    public void setBankUserId(Long bankUserId) {
        this.bankUserId = bankUserId;
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
    public static AccountDto fromModel(Account account){
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setBankUserId(account.getBankUser().getId());
        dto.setAmount(account.getAmount());
        dto.setCurrency(account.getCurrency());
        dto.setCreatedDate(account.getCreatedDate());
        return dto;

    }
}
