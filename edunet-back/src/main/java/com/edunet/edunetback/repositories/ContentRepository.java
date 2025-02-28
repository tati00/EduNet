package com.edunet.edunetback.repositories;

import com.edunet.edunetback.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Integer>{
    
}
