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
@RequestMapping("/admin/users")
public class UserController {
    private UserRepository repository;
    private UserModelAssembler assembler;

    public UserController(UserRepository userRepository,UserModelAssembler userModelAssembler){
        this.repository = userRepository;
        this.assembler = userModelAssembler;
    }

    @GetMapping("")
    public CollectionModel<EntityModel<UserWithAccountDto>> all(){
        List<EntityModel<UserWithAccountDto>> entityModelList = repository.findAll().stream()
                .map(UserWithAccountDto::fromModel)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(entityModelList,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<UserWithAccountDto> one(@PathVariable Long id){
        return repository.findById(id)
                .map(UserWithAccountDto::fromModel)
                .map(assembler::toModel)
                .orElseThrow( () -> new UserNotFoundException(id) );
    }
}
