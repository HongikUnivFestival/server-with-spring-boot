package com.hiufestainfo.domain.image.repository;

import com.hiufestainfo.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<Image,Long> {

}
