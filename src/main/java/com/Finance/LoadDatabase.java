package com.Finance;

import com.Finance.Account.Account;
import com.Finance.Account.AccountRepository;
import com.Finance.User.User;
import com.Finance.User.UserRepository;
import org.antlr.v4.runtime.tree.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
public class LoadDatabase {
    private static Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository accountRepository, UserRepository userRepository){
        return args -> {
            User userTemp = userRepository.save(new User("user1","112233"));
            accountRepository.save(new Account(13.0d, Currency.USD));
            accountRepository.save(new Account(15000.0d, Currency.RUB));
            accountRepository.save(new Account(180.0d, Currency.USDT));




            //userTemp.getAccounts().forEach((acc)->acc.setOwnerId(userTemp.getId()));


            accountRepository.findAll().forEach(account -> {userTemp.addAccount(account); log.info("Preloaded " + account);});
            userRepository.save(userTemp);
            userRepository.findAll().forEach(user -> log.info("Preloaded "+ user));


        };

    }
}
