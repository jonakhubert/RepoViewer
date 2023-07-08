package com.example.repoviewer.Models.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RepoResponse(
        @JsonProperty("full_name") String repositoryName,
        @JsonProperty("language") String language,
        @JsonProperty("visibility") String visibility,
        @JsonProperty("url") String url
) {}