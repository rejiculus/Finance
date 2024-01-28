package com.Finance;

public enum Currency {
    USD(1.0d),
    RUB(1.0d/95),
    EUR(1.2d),
    BTC(44000.0d),
    ETH(2200.0d),
    BNB(300.0d),
    USDT(1.0d);
    private double usdRate;
    Currency(double rate){
        this.usdRate=rate;
    }
    public double getRate(){
        return usdRate;
    }
    public static Currency getCurrencyFromName(String cur){
        return switch (cur){
            case "USD"->Currency.USD;
            case "RUB"->Currency.RUB;
            case "EUR"->Currency.EUR;
            case "BTC"->Currency.BTC;
            case "ETH"->Currency.ETH;
            case "BNB"->Currency.BNB;
            case "USDT"->Currency.USDT;
            default -> throw new CurrencyNotFoundException(cur);
        };
    }

    @Override
    public String toString() {
        return switch (this){
            case USD -> "USD";
            case BNB -> "BNB";
            case BTC -> "BTC";
            case ETH -> "ETH";
            case EUR -> "EUR";
            case RUB -> "RUB";
            case USDT -> "USDT";
            default -> "How you get this value?";
        };
    }
}
