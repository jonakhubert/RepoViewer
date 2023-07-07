package com.example.repoviewer.Model;

public record Repo(String full_name, String visibility, String languages_url) {

    public String getRepositoryName() {
        return full_name;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getLanguagesUrl() {
        return languages_url;
    }
}