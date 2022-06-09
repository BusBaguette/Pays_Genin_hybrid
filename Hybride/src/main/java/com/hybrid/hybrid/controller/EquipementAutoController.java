package com.hybrid.hybrid.controller;

import com.hybrid.hybrid.Mapper;
import com.hybrid.hybrid.repository.EquipementAutoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipement")
public class EquipementAutoController {

    @Autowired
    private EquipementAutoRepositoryInterface repository;

    private Mapper mapper;

    public EquipementAutoController(EquipementAutoRepositoryInterface repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


}
