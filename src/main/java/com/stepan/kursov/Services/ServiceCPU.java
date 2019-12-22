package com.stepan.kursov.Services;

import com.stepan.kursov.Model.CPU;
import com.stepan.kursov.Repository.CRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ServiceCPU {

    @Autowired
    private CRUDRepository crudRepository;

    public String GetById(Integer id) {
        System.out.println("Запрашивает 1 объект по id");
        return  crudRepository.findById(id).toString();
    }

    public String getCPUsForFilter (String manufacturer, String core,
                             String socket, String count_core,
                             String count_flow, String frequency,
                             String inserted_GPU) {


        return null;
    }

}
