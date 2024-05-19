package com.andrewsalygin.repository;

import com.andrewsalygin.dto.recipe.RecipeFullInfoDTO;
import java.util.List;

public interface RecipesRepository {

    RecipeFullInfoDTO getRecipe(Integer recipeId);

    List<RecipeFullInfoDTO> getRecipes(Integer limit, Integer offset);

    void deleteRecipe(Integer recipeId);
}
