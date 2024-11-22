package com.andrewsalygin.controller.recipes;

import com.andrewsalygin.hospital.api.RecipesApi;
import com.andrewsalygin.hospital.model.RecipeFullInfo;
import com.andrewsalygin.service.interfaces.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class RecipesController implements RecipesApi {

    private final RecipesService recipesService;

    @Override
    public ResponseEntity<RecipeFullInfo> getRecipe(Integer recipeId) {
        return recipesService.getRecipe(recipeId);
    }

    @Override
    public ResponseEntity<List<RecipeFullInfo>> getRecipes(Integer limit, Integer offset) {
        return recipesService.getRecipes(limit, offset);
    }
}
