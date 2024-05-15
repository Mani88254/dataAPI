package com.Medicalapplication.Diet.Controller;

import java.util.ArrayList;
import java.util.List;

import com.Medicalapplication.Diet.Entity.DietRecipeEntity;
import com.Medicalapplication.Diet.Service.DietApplicationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diet")
public class DietApplicationController {

	@Autowired
    private final DietApplicationImpl dietApplication;
    
    public List<DietRecipeEntity> dietappliaction = new ArrayList<>();

    @Autowired
    public DietApplicationController(DietApplicationImpl dietApplication) {
        this.dietApplication = dietApplication;
    }
  

    @GetMapping("/recipes")
    public ResponseEntity<List<DietRecipeEntity>> getAllRecipes() {
        try {
            List<DietRecipeEntity> recipes = dietApplication.getAllRecipe();
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/recipes/{id}")
    public ResponseEntity<DietRecipeEntity> getRecipeById(@PathVariable Long id) {
        try {
            DietRecipeEntity recipe = dietApplication.getRecipeById(id);
            if (recipe != null) {
                return new ResponseEntity<>(recipe, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/recipes")
    public ResponseEntity<DietRecipeEntity> createRecipe(@RequestBody DietRecipeEntity recipe) {
        try {
            DietRecipeEntity savedRecipe = dietApplication.saveRecipe(recipe);
            return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/recipes/{id}")
    public ResponseEntity<DietRecipeEntity> updateRecipe(@PathVariable Long id, @RequestBody DietRecipeEntity updatedRecipe) {
        try {
            DietRecipeEntity recipe = dietApplication.updateRecipe(id, updatedRecipe);
            if (recipe != null) {
                return new ResponseEntity<>(recipe, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        try {
            dietApplication.deleteRecipe(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
