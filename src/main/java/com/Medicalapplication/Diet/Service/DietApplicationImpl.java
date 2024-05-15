package com.Medicalapplication.Diet.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.Medicalapplication.Diet.Dao.DietApplicatioDao;
import com.Medicalapplication.Diet.Entity.DietRecipeEntity;

@Service
public class DietApplicationImpl {

	@Autowired
	private final DietApplicatioDao dietApplicationDao;
	
	public DietApplicationImpl(DietApplicatioDao dietApplicationDao) {
		this.dietApplicationDao = dietApplicationDao;
	}
	
	public List<DietRecipeEntity> getAllRecipe(){
		
		return dietApplicationDao.findAll();
	}
	
	public DietRecipeEntity getRecipeById(Long id) {
        Optional<DietRecipeEntity> optionalRecipe = dietApplicationDao.findById(id);
        return optionalRecipe.orElse(null);
    }
	
	public DietRecipeEntity saveRecipe(DietRecipeEntity recipe) {
        return dietApplicationDao.save(recipe);
    }
    
    public DietRecipeEntity updateRecipe(Long id, DietRecipeEntity updatedRecipe) {
        Optional<DietRecipeEntity> optionalRecipe = dietApplicationDao.findById(id);
        if (optionalRecipe.isPresent()) {
            DietRecipeEntity existingRecipe = optionalRecipe.get();
            existingRecipe.setBreakfast(updatedRecipe.getBreakfast());
            existingRecipe.setDinner(updatedRecipe.getDinner());
            // Update other fields as needed
            return dietApplicationDao.save(existingRecipe);
        } else {
            return null;
        }
    }
    
    public void deleteRecipe(Long id) {
        dietApplicationDao.deleteById(id);
    }

}
