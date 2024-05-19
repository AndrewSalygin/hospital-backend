package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.RecipeFullInfo;
import com.andrewsalygin.repository.interfaces.RecipesRepository;
import com.andrewsalygin.service.interfaces.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
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
