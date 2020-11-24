package com.example.recipe.services;

import com.example.recipe.commands.IngredientCommand;
import com.example.recipe.commands.UnitOfMeasureCommand;
import com.example.recipe.converters.IngredientCommandToIngredient;
import com.example.recipe.converters.IngredientToIngredientCommand;
import com.example.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.example.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.recipe.domain.Ingredient;
import com.example.recipe.domain.Recipe;
import com.example.recipe.domain.UnitOfMeasure;
import com.example.recipe.repositories.RecipeRepository;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }


    @BeforeEach
    void setUp() {

       MockitoAnnotations.initMocks(this);

       ingredientService = new IngredientServiceImpl(recipeService, ingredientToIngredientCommand, ingredientCommandToIngredient,unitOfMeasureRepository) ;
    }

    @Test
    public void findByRecipeIdAndId() {
    }

    @Test
    public void findByRecipeIdAndIngredientId (){
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        Ingredient ingredient3 = new Ingredient();

        ingredient1.setId(1L);
        ingredient2.setId(2L);
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        //when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //then
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeService, times(1)).findById(anyLong());

    }

    @Test
    public void saveIngredientCommand(){
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);

        UnitOfMeasureCommand unitOfMeasureC = new UnitOfMeasureCommand();
        unitOfMeasureC.setId(1L);

        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);
        command.setDescription("Description");
        command.setAmount(new BigDecimal(1));
        command.setUom(unitOfMeasureC);



        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);
        savedRecipe.getIngredients().iterator().next().setDescription("Description");
        savedRecipe.getIngredients().iterator().next().setAmount(new BigDecimal(1));
        savedRecipe.getIngredients().iterator().next().setUom(unitOfMeasure);

        when(recipeService.findById(anyLong())).thenReturn(new Recipe());
        when(recipeService.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeService, times(1)).findById(anyLong());
        verify(recipeService, times(1)).save(any(Recipe.class));

    }

    @Test
    void  deleteById(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        recipe.addIngredient(new Ingredient());
        recipe.getIngredients().iterator().next().setId(2L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        //given
        Long idToDelete = 2L;

        //when
        ingredientService.deleteById(anyLong(),idToDelete);

        //then

        verify(recipeService,times(1)).save(any());
        verify(recipeService,times(1)).findById(anyLong());


    }

}
