package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.RecipeFullInfo;
import com.andrewsalygin.repository.RecipesRepository;
import com.andrewsalygin.service.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcRecipesService implements RecipesService {

    private final RecipesRepository recipesRepository;

    @Override
    public ResponseEntity<RecipeFullInfo> getRecipe(Integer recipeId) {
        return null;
    }

    @Override
    public ResponseEntity<List<RecipeFullInfo>> getRecipes(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteRecipe(Integer recipeId) {
        return null;
    }
}
