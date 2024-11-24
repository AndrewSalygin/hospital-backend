package com.andrewsalygin.applicationservice.repository.interfaces;

import com.andrewsalygin.applicationservice.dto.recipe.RecipeFullInfoDTO;
import java.util.List;

public interface RecipesRepository {

    RecipeFullInfoDTO getRecipe(Integer recipeId);

    List<RecipeFullInfoDTO> getRecipes(Integer limit, Integer offset);

    void deleteRecipe(Integer recipeId);
}
