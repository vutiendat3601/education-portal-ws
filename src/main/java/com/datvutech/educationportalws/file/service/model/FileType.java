package com.datvutech.educationportalws.file.service.model;

public enum FileType {
    PROFILE_IMAGE,
    BACKGROUND_IMAGE;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
