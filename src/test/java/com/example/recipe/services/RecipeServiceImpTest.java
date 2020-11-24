package com.example.recipe.services;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.converters.RecipeCommandToRecipe;
import com.example.recipe.converters.RecipeToRecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.exceptions.NotFoundException;
import com.example.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RecipeServiceImpTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {

        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when (recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(),1);
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    void getRecipesById(){

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe returnedRecipe =  recipeService.findById(1L);

        assertNotNull(returnedRecipe,"Null recipe returned");
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();

    }

    @Test
    void getCommandById(){

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand returnedCommand  = recipeService.findCommandById(1L);
        assertNotNull(returnedCommand,"No command returned");
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
        verify(recipeToRecipeCommand,times(1)).convert(any());

    }

    @Test
    void  deleteById(){
        //given
        Long idToDelete = 2L;

        //when
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeRepository,times(1)).deleteById(anyLong());

    }

    @Test
    public void getRecipeByIdTestNotFound() throws Exception {
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Assertions.assertThrows(NotFoundException.class, () -> {recipeService.findById(1L);});

        //should go boom
    }

    @Test
    public void getRecipeByIdNumberFormatException() throws Exception{

        Assertions.assertThrows(NumberFormatException.class,()->{
            recipeService.findById(Long.valueOf("asd"));
        });
    }



}