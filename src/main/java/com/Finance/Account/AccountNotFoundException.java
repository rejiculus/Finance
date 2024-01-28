package com.Finance.Account;

public class AccountNotFoundException extends RuntimeException {
    AccountNotFoundException(Long id){
        super(String.format("Account with id=%d not found",id));
    }
}
