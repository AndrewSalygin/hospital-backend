package com.andrewsalygin.applicationservice.controller.recipes;

import com.andrewsalygin.applicationservice.api.SuperAdminRecipesApi;
import com.andrewsalygin.applicationservice.service.interfaces.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuperAdminRecipesController implements SuperAdminRecipesApi {

    private final RecipesService recipesService;

    @Override
    public ResponseEntity<Void> deleteRecipe(Integer recipeId) {
        return recipesService.deleteRecipe(recipeId);
    }
}
