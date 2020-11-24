package com.example.recipe.repositories;

import com.example.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface RecipeRepository extends CrudRepository <Recipe, Long> {


}
