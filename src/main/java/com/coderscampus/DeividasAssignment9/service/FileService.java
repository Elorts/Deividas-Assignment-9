package com.coderscampus.DeividasAssignment9.service;

import com.coderscampus.DeividasAssignment9.domain.Recipe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    Boolean fileRed = false;
    List<Recipe> listOfRecipes = new ArrayList<Recipe>();

    public List<Recipe> glutenFree() throws IOException {
        if (!fileRed) {
            readLines();
        }
        List<Recipe> filteredListOfRecipes = new ArrayList<>();
        for (Recipe filteredRecipe : listOfRecipes) {
            if (filteredRecipe.getGlutenFree()) {
                filteredListOfRecipes.add(filteredRecipe);
            }
        }
        return filteredListOfRecipes;
    }

    public List<Recipe> vegan() throws IOException {
        if (!fileRed) {
            readLines();
        }
        List<Recipe> filteredListOfRecipes = new ArrayList<>();
        for (Recipe filteredRecipe : listOfRecipes) {
            if (filteredRecipe.getVegan()) {
                filteredListOfRecipes.add(filteredRecipe);
            }
        }
        return filteredListOfRecipes;
    }

    public List<Recipe> veganAndGlutenFree() throws IOException {
        if (!fileRed) {
            readLines();
        }
        List<Recipe> filteredListOfRecipes = new ArrayList<>();
        for (Recipe filteredRecipe : listOfRecipes) {
            if (filteredRecipe.getVegan() && filteredRecipe.getGlutenFree()) {
                filteredListOfRecipes.add(filteredRecipe);
            }
        }
        return filteredListOfRecipes;
    }

    public List<Recipe> vegetarian() throws IOException {
        if (!fileRed) {
            readLines();
        }
        List<Recipe> filteredListOfRecipes = new ArrayList<>();
        for (Recipe filteredRecipe : listOfRecipes) {
            if (filteredRecipe.getVegetarian()) {
                filteredListOfRecipes.add(filteredRecipe);
            }
        }
        return filteredListOfRecipes;
    }

    public List<Recipe> allRecipes() throws IOException {
        if (!fileRed) {
            readLines();
        }
        return listOfRecipes;
    }

    @Autowired
    private ResourceLoader resourceLoader;

    public void readLines() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:recipes.txt");
        File file = resource.getFile();

        CSVParser parser = new CSVParser(new FileReader(String.valueOf(file)), CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreSurroundingSpaces().withEscape('\\'));
        for (CSVRecord record : parser) {
            Recipe recipe = new Recipe();
            recipe.setCookingMinutes(Integer.parseInt(record.get("Cooking Minutes")));
            recipe.setDairyFree(Boolean.parseBoolean(record.get("Dairy Free")));
            recipe.setGlutenFree(Boolean.parseBoolean(record.get("Gluten Free")));
            recipe.setInstructions(record.get("Instructions"));
            recipe.setPreparationMinutes(Double.parseDouble(record.get("Preparation Minutes")));
            recipe.setPricePerServing(Double.parseDouble(record.get("Price Per Serving")));
            recipe.setReadyInMinutes(Integer.parseInt(record.get("Ready In Minutes")));
            recipe.setServings(Integer.parseInt(record.get("Servings")));
            recipe.setSpoonacularScore(Double.parseDouble(record.get("Spoonacular Score")));
            recipe.setTitle(record.get("Title"));
            recipe.setVegan(Boolean.parseBoolean(record.get("Vegan")));
            recipe.setVegetarian(Boolean.parseBoolean(record.get("Vegetarian")));

            listOfRecipes.add(recipe);
        }
        parser.close();
    }
}
