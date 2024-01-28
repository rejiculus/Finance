package com.Finance.User;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository repository;
    private UserModelAssembler assembler;

    @GetMapping("")
    public CollectionModel<EntityModel<User>> all(){
        List<EntityModel<User>> entityModelList = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(entityModelList,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<User> one(@PathVariable Long id){
        return repository.findById(id)
                .map(assembler::toModel)
                .orElseThrow( () -> new UserNotFoundException(id) );
    }
}
