package com.andrewsalygin.controller.recipes;

import com.andrewsalygin.hospital.api.SuperAdminRecipesApi;
import com.andrewsalygin.service.interfaces.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminRecipesController implements SuperAdminRecipesApi {

    private final RecipesService recipesService;

    @Override
    public ResponseEntity<Void> deleteRecipe(Integer recipeId) {
        return recipesService.deleteRecipe(recipeId);
    }
}
