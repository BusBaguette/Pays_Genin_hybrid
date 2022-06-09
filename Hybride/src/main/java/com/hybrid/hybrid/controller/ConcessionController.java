package com.hybrid.hybrid.controller;

import com.hybrid.hybrid.Mapper;
import com.hybrid.hybrid.repository.ConcessionRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concession")
public class ConcessionController {

    @Autowired
    private ConcessionRepositoryInterface repository;

    private Mapper mapper;

    public ConcessionController(ConcessionRepositoryInterface repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
