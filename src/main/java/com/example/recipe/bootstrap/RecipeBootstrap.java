package com.example.recipe.bootstrap;

import com.example.recipe.domain.*;
import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.repositories.RecipeRepository;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Profile("default")
public class RecipeBootstrap implements ApplicationListener <ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public RecipeBootstrap(UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Data Loaded");
    }

    private List<Recipe> getRecipes (){

        List<Recipe> recipes = new ArrayList<>( 2);

        //Units of Measure
        Optional <UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        if (!teaspoonUomOptional.isPresent()){
           throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByUom("Tablespoon");
        if (!tablespoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByUom("Cup");
        if (!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByUom("Pinch");
        if (!pinchUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByUom("Ounce");
        if (!ounceUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByUom("Dash");
        if (!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByUom("Unit");
        if (!unitOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> cloveUomOptional = unitOfMeasureRepository.findByUom("Clove");
        if (!cloveUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> poundsUomOptional = unitOfMeasureRepository.findByUom("Pounds");
        if (!poundsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional <UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByUom("Pint");
        if (!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure teaSpoonUom = teaspoonUomOptional.get();
        UnitOfMeasure tableSpoonUom = tablespoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure unitUom = unitOptional.get();
        UnitOfMeasure pintUom = pinchUomOptional.get();

        //Categories
        Optional <Category> mexCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }
        Optional <Category> ameCategoryOptional = categoryRepository.findByDescription("American");
        if (!ameCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category mexicanCategory = mexCategoryOptional.get();
        Category americanCategory = ameCategoryOptional.get();

        //Recipes

        //Guacamole
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setSource("Simple Recipes");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Do not overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), unitUom));
        guacamoleRecipe.addIngredient(new Ingredient("salt", new BigDecimal(".25"), teaSpoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tableSpoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(".25"), cupUom));
        guacamoleRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), unitUom));
        guacamoleRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tableSpoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom));
        guacamoleRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), unitUom));

        guacamoleRecipe.getCategories().add(mexicanCategory);
        guacamoleRecipe.getCategories().add(americanCategory);

        recipes.add(guacamoleRecipe);

        //Chicken Tacos
        Recipe chickTacosRecipe = new Recipe();
        chickTacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        chickTacosRecipe.setPrepTime(30);
        chickTacosRecipe.setServings(4);
        chickTacosRecipe.setCookTime(30);
        chickTacosRecipe.setDifficulty(Difficulty.MODERATE);
        chickTacosRecipe.setSource("Simple Recipes");
        chickTacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickTacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");
        Notes chickenTacosNotes = new Notes();
        chickenTacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        chickTacosRecipe.setNotes(chickenTacosNotes);

        chickTacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), unitUom));
        chickTacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom));
        chickTacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), unitUom));
        chickTacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        chickTacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), unitUom));
        chickTacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), unitUom));
        chickTacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        chickTacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), unitUom));
        chickTacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), unitUom));
        chickTacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        chickTacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), unitUom));

        chickTacosRecipe.getCategories().add(mexicanCategory);


        recipes.add(chickTacosRecipe);

        return  recipes;
    }


}
