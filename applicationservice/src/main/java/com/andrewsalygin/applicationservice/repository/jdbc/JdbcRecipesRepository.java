package com.andrewsalygin.applicationservice.repository.jdbc;

import com.andrewsalygin.applicationservice.repository.interfaces.RecipesRepository;
import com.andrewsalygin.applicationservice.dto.recipe.RecipeFullInfoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcRecipesRepository implements RecipesRepository {

    private final JdbcClient client;

    @Override
    public RecipeFullInfoDTO getRecipe(Integer recipeId) {
        return client.sql("SELECT recipeId, admissionDateTime, expirationDate, medicationName, " +
                "medicationForm, dosage, patientLastName, patientFirstName, patientMiddleName, " +
                "doctorLastName, doctorFirstName, doctorMiddleName, isDeleted " +
                "FROM recipeV WHERE recipeId = ?")
            .param(recipeId)
            .query(RecipeFullInfoDTO.class)
            .single();
    }

    @Override
    public List<RecipeFullInfoDTO> getRecipes(Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query = "SELECT recipeId, admissionDateTime, expirationDate, medicationName, medicationForm, dosage, " +
                "patientLastName, patientFirstName, patientMiddleName, doctorLastName, doctorFirstName, " +
                "doctorMiddleName, isDeleted " +
                "FROM recipeV " +
                "ORDER BY recipeId " +
                "OFFSET :offset ROWS";
        } else {
            query = "SELECT recipeId, admissionDateTime, expirationDate, medicationName, medicationForm, dosage, " +
                "patientLastName, patientFirstName, patientMiddleName, doctorLastName, doctorFirstName, " +
                "doctorMiddleName, isDeleted " +
                "FROM recipeV " +
                "ORDER BY recipeId " +
                "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("offset", offset)
            .param("limit", limit)
            .query(RecipeFullInfoDTO.class)
            .list();
    }

    @Override
    public void deleteRecipe(Integer recipeId) {
        client.sql("UPDATE recipe SET isDeleted = 1 WHERE recipeId = ?")
            .param(recipeId)
            .update();
    }
}
