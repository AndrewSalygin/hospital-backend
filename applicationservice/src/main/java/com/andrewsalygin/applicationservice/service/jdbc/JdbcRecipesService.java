package com.andrewsalygin.applicationservice.service.jdbc;

import com.andrewsalygin.applicationservice.dto.recipe.RecipeFullInfoDTO;
import com.andrewsalygin.applicationservice.model.RecipeFullInfo;
import com.andrewsalygin.applicationservice.repository.interfaces.RecipesRepository;
import com.andrewsalygin.applicationservice.service.interfaces.RecipesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcRecipesService implements RecipesService {

    private final RecipesRepository recipesRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<RecipeFullInfo> getRecipe(Integer recipeId) {
        RecipeFullInfoDTO recipeDTO = recipesRepository.getRecipe(recipeId);
        RecipeFullInfo recipe = modelMapper.map(recipeDTO, RecipeFullInfo.class);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RecipeFullInfo>> getRecipes(Integer limit, Integer offset) {
        List<RecipeFullInfoDTO> recipeDTOs = recipesRepository.getRecipes(limit, offset);
        Type listType = new TypeToken<List<RecipeFullInfo>>() {}.getType();
        List<RecipeFullInfo> recipes = modelMapper.map(recipeDTOs, listType);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteRecipe(Integer recipeId) {
        recipesRepository.deleteRecipe(recipeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
