package com.scm.services.impl;

import com.scm.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {


    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {

        // code likhnaa hia jo image ko upload kar rha ho


        return "nawaz quazi";
        // and return raha hoga : url

    }

    @Override
    public String getUrlFromPublicId(String fileName) {
        return "nawaz quazi";

    }

}
