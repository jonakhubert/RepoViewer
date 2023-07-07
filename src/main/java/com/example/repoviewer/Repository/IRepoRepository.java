package com.example.repoviewer.Repository;

import com.example.repoviewer.Model.Response.RepoResponse;

import java.util.List;

public interface IRepoRepository {
    List<RepoResponse> getRepositoriesByUsername(String username);
    String getMostUsedLanguage(String languagesUrl);
}
