package com.example.tacocloudapplication.repo.impl;

import com.example.tacocloudapplication.table.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

    List<Ingredient> findAll();
}
