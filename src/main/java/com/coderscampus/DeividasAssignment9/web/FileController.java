package com.coderscampus.DeividasAssignment9.web;

import com.coderscampus.DeividasAssignment9.domain.Recipe;
import com.coderscampus.DeividasAssignment9.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
public class FileController {

//    @Autowired
//    private FileService fileService;

    @GetMapping("/gluten-free")
    public List<Recipe> getGlutenFree () throws IOException {

        FileService fileService = new FileService();
        return fileService.glutenFree();
    }

    @GetMapping("/vegan")
    public List<Recipe> getVegan () throws IOException {

        FileService fileService = new FileService();
        return fileService.vegan();
    }

    @GetMapping("/vegan-and-gluten-free")
    public List<Recipe> getVeganAndGlutenFree () throws IOException {

        FileService fileService = new FileService();
        return fileService.veganAndGlutenFree();
    }

    @GetMapping("/vegetarian")
    public List<Recipe> getVegeterian () throws IOException {

        FileService fileService = new FileService();
        return fileService.vegetarian();
    }

    @GetMapping("/all-recipes")
    public List<Recipe> getAllRecipes() throws IOException {

        FileService fileService = new FileService();
        return fileService.allRecipes();
    }

}
