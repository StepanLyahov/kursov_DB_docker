package com.stepan.kursov.Repository;

import com.stepan.kursov.Model.CPU;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MyReposIntImpl implements MyReposInt {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List hardRequest(String request) {
        return em.createQuery(request, CPU.class)
                .getResultList();
    }
}
