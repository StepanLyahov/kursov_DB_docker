package com.stepan.kursov.Repository;

import com.stepan.kursov.Model.CPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// MyReposInt<CPU>
@Repository
public interface CRUDRepository extends CrudRepository<CPU, Integer>, MyReposInt<CPU> {

}
