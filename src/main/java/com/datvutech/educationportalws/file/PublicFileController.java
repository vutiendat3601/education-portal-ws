package com.datvutech.educationportalws.file;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datvutech.educationportalws.file.service.FileService;
import com.datvutech.educationportalws.file.service.model.FileType;
import com.datvutech.educationportalws.user.service.UserService;

@RequestMapping("public/files")
@RestController
public class PublicFileController {

    private final FileService fileService;

    public PublicFileController(UserService userService, FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("profile_images/{fileName}")
    public ResponseEntity<Resource> loadUserProfileImage(@PathVariable String fileName) {
        Resource profileImg = fileService.getResouceFile(fileName, FileType.PROFILE_IMAGE);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(profileImg);
    }

    @GetMapping("background_images/{fileName}")
    public ResponseEntity<Resource> loadUserBackgroundImage(@PathVariable String fileName) {
        Resource backgroundImg = fileService.getResouceFile(fileName, FileType.BACKGROUND_IMAGE);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(backgroundImg);
    }
}
