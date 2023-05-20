package com.datvutech.educationportalws.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.datvutech.educationportalws.file.service.model.FileType;

import net.bytebuddy.utility.RandomString;

@PropertySource("classpath:app-configs.properties")
@Service
public class FileService {

    @Value("${file.length-of-random-file-name}")
    private int lengthOfRandomFileName;

    private final Path uploadDir;

    public FileService() {
        uploadDir = Paths.get(System.getProperty("user.home")).resolve("education-portal/");
        FileType[] fileTypes = FileType.values();
        for (FileType type : fileTypes) {
            Path typePath = uploadDir.resolve(type + "/");
            if (!Files.exists(typePath)) {
                try {
                    Files.createDirectories(typePath);
                } catch (IOException e) {
                    System.out.println("The " + type + " directory can't be created!");
                }
            }
        }
    }

    /**
     * 
     * @param file
     * @param type the type of file, following the
     *             com.datvutech.educationportalws.file.service.model.FileType enum
     *             [ PROFILE_IMAGE | BACKGROUND_IMAGE]
     * @return the only fileName (without the path to the file)
     */
    public String saveFile(MultipartFile file, FileType type) {
        String fileName = RandomString.make(lengthOfRandomFileName);
        fileName += "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream,
                    uploadDir.resolve(type + "/" + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
        } catch (IOException e) {
            System.out.println("The " + fileName + " can't be uploaded!");
        }
        return fileName;
    }

    public Resource getResouceFile(String fileName, FileType type) {
        return new FileSystemResource(uploadDir.resolve(type + "/" + fileName));
    }

}
