package com.shop.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.web.models.Type;
import com.shop.web.repository.TypeRepository;
import com.shop.web.service.TypeService;

@Service
public class TypeServiceImp implements TypeService{

    private TypeRepository typeRepository;

    public TypeServiceImp(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }
    
}
