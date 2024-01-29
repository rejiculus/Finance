package com.Finance.Account;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/admin/accounts")
public class AccountController {
    private AccountRepository repository;
    private AccountModelAssembler assembler;

    AccountController(AccountRepository accountRepository, AccountModelAssembler accountModelAssembler){
        this.repository = accountRepository;
        this.assembler = accountModelAssembler;
    }
    @GetMapping("")
    public CollectionModel<EntityModel<Account>> all() {
        List<EntityModel<Account>> accounts = repository.findAll().stream()
                                                    .map(assembler::toModel)
                                                    .collect(Collectors.toList());

        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
    }

    @RequestMapping("/")
    public void handleRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect( linkTo(methodOn(AccountController.class).all()).toString() );
    }

    @PostMapping("")
    public ResponseEntity<?> crateAccount(@RequestBody Account account){
        EntityModel<Account> entityModel = assembler.toModel(repository.save(account));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }


    @GetMapping("/{id}")
    public EntityModel<Account> one(@PathVariable Long id){
        return repository.findById(id)
                .map(assembler::toModel)
                .orElseThrow( () -> new AccountNotFoundException(id) );
    }
}
