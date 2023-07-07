package com.example.repoviewer.Model.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RepoResponse(
        @JsonProperty("full_name") String repositoryName,
        @JsonProperty("language") String language,
        @JsonProperty("visibility") String visibility
) {
    public RepoResponse(String repositoryName, String language, String visibility) {
        this.repositoryName = repositoryName;
        this.language = language;
        this.visibility = visibility;
    }
}