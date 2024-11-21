package com.chiendang.cooking.api.image.service;

import com.chiendang.cooking.api.recipe.entity.Recipe;
import com.chiendang.cooking.api.recipe.respository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    RecipeRepository recipeRepository;
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        //get name of the file
        String fileName = file.getOriginalFilename();

        //to get the file path
        String filePath = path + File.separator + fileName;

        //create a file object
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //copy the file or upload file to the path
        Files.copy(file.getInputStream(), Paths.get(filePath));

        Recipe recipe = new Recipe();
        recipe.setImage(filePath);
        recipeRepository.save(recipe);
        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
}
