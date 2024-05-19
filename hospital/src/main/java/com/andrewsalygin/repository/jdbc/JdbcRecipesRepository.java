package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.recipe.RecipeFullInfoDTO;
import com.andrewsalygin.hospital.model.RecipeFullInfo;
import com.andrewsalygin.repository.RecipesRepository;
import com.andrewsalygin.service.RecipesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcRecipesRepository implements RecipesRepository {

    private final JdbcClient client;

    @Override
    public RecipeFullInfoDTO getRecipe(Integer recipeId) {
        return null;
    }

    @Override
    public List<RecipeFullInfoDTO> getRecipes(Integer limit, Integer offset) {
        return List.of();
    }

    @Override
    public void deleteRecipe(Integer recipeId) {

    }
}
