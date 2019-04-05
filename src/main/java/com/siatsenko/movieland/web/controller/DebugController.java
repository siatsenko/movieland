package com.siatsenko.movieland.web.controller;

import com.siatsenko.movieland.service.EnrichmentService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DebugController {

    @Autowired
    private List<? extends EnrichmentService> list;

    @Autowired
    private ObjectFactory<? extends PrototypeClass> objectFactory;

    @GetMapping(path = "/list")
    public List<String> getEnrichmentService() {
        List<String> result = new ArrayList<>();
        for (EnrichmentService service : list) {
            result.add(service.getClass().toString());
        }
        return result;
    }

    @GetMapping(path = "/prototype")
    public List<String> getProtorypeClass() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PrototypeClass instance = objectFactory.getObject();
            result.add(instance.toString());
        }
        return result;
    }

    @Component
    @Scope("prototype")
    public class PrototypeClass {

    }

}
