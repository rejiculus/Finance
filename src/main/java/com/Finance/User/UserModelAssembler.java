package com.Finance.User;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserWithAccountDto, EntityModel<UserWithAccountDto>> {
    @Override
    public EntityModel<UserWithAccountDto> toModel(UserWithAccountDto user){
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).all()).withRel("users"),
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel());
    }
}
