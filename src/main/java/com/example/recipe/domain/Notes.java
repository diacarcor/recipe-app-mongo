package com.example.recipe.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Notes {

    private String id;

    private Recipe recipe;

    private String recipeNotes;
}
