package com.Finance;

public class CurrencyNotFoundException extends RuntimeException{
    CurrencyNotFoundException(String name){
        super(String.format("Currency %s not found in local DB",name));
    }
}
