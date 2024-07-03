package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Category;

public interface CategoryDao extends JpaRepository<Category,Long> {

}
