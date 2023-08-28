package com.hiufestainfo.domain.example.repository;

import com.hiufestainfo.domain.example.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example,Long> {
}
