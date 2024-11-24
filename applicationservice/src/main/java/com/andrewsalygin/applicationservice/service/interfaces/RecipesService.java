package com.andrewsalygin.applicationservice.service.interfaces;

import com.andrewsalygin.applicationservice.model.RecipeFullInfo;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface RecipesService {

    ResponseEntity<RecipeFullInfo> getRecipe(Integer recipeId);

    ResponseEntity<List<RecipeFullInfo>> getRecipes(Integer limit, Integer offset);

    ResponseEntity<Void> deleteRecipe(Integer recipeId);
}
