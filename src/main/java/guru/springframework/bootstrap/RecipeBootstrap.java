package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    UnitOfMeasureRepository unitOfMeasureRepository;
    CategoryRepository categoryRepository;
    RecipeRepository recipeRepository;

    public RecipeBootstrap(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            recipeRepository.saveAll(getRecipes());
    }
    public List<Recipe> getRecipes(){
        List<Recipe> recipeList = new ArrayList<>(2);
        Optional<UnitOfMeasure> eachUnitOfMeasure = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> teaspoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoons");
        if(!teaspoonUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> tablespoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoons");
        if(!tablespoonUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> dashUnitOfMeasure = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> pintUnitOfMeasure = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> cupUnitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }
        //get UOMs from Option
        UnitOfMeasure eachUOM = eachUnitOfMeasure.get();
        UnitOfMeasure teaspoonUOM = teaspoonUnitOfMeasure.get();
        UnitOfMeasure tablespoonUOM = tablespoonUnitOfMeasure.get();
        UnitOfMeasure dashUOM = dashUnitOfMeasure.get();
        UnitOfMeasure pintUOM = pintUnitOfMeasure.get();
        UnitOfMeasure cupUOM = cupUnitOfMeasure.get();

        Optional<Category> usaCategoryOptional = categoryRepository.findByDescription("American");
        if(!usaCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }
        //get UOMs from Option
        Category usaCategory = usaCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Perfect Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("METHOD\n" +
                "1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("Ripe Avocados", new BigDecimal(2),eachUOM,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt",new BigDecimal("0.5"), teaspoonUOM,guacRecipe));

        //usaCategory.getRecipes().add(guacRecipe);
        //mexicanCategory.getRecipes().add(guacRecipe);

        guacRecipe.getCategories().add(usaCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return the list
        recipeList.add(guacRecipe);

        return recipeList;

    }


}
















