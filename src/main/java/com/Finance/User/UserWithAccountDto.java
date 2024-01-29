package com.Finance.User;

import com.Finance.Account.Account;
import com.Finance.Account.AccountDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class UserWithAccountDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("accounts")
    private List<AccountDto> accountDtoList;

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

    public List<AccountDto> getAccountDtoList() {
        return accountDtoList;
    }

    public void setAccountDtoList(List<AccountDto> accountDtoList) {
        this.accountDtoList = accountDtoList;
    }
    public static UserWithAccountDto fromModel(User user){
        UserWithAccountDto dto = new UserWithAccountDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());

        List<AccountDto> accountDtos = new ArrayList<>();
        for(Account a: user.getAccounts()){
            accountDtos.add(AccountDto.fromModel(a));
        }
        dto.setAccountDtoList(accountDtos);

        return dto;
    }
}
