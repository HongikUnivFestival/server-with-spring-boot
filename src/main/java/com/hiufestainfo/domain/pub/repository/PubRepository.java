package com.hiufestainfo.domain.pub.repository;

import com.hiufestainfo.domain.pub.entity.Pub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PubRepository extends JpaRepository<Pub,Long> {
   List<Pub> findByDepartment(String department);
}
