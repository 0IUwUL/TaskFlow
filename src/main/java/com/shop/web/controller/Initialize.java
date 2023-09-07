package com.shop.web.controller;

import org.springframework.stereotype.Component;

import com.shop.web.models.Role;
import com.shop.web.models.Type;
import com.shop.web.repository.RoleRepository;
import com.shop.web.repository.TypeRepository;

import jakarta.annotation.PostConstruct;

@Component
public class Initialize {
    private final RoleRepository roleRepo;
    private final TypeRepository typeRepo;

    public Initialize(RoleRepository roleRepo, TypeRepository typeRepo) {
        this.roleRepo = roleRepo;
        this.typeRepo = typeRepo;
    }

    @PostConstruct
    public void init(){
        if(roleRepo.count()==0){
            Role role1 = new Role();
            role1.setTitle("ADMIN");

            roleRepo.save(role1);

            Role role2 = new Role();
            role2.setTitle("USER");
            roleRepo.save(role2);
        }

        if(typeRepo.count()==0){
            Type type1 = new Type();
            type1.setTitle("To Do");

            typeRepo.save(type1);

            Type type2 = new Type();
            type2.setTitle("In Progress");

            typeRepo.save(type2);

            Type type3 = new Type();
            type3.setTitle("Done");

            typeRepo.save(type3);
        }
        


    }
}
