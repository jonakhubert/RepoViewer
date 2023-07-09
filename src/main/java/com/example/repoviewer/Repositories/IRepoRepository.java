package com.example.repoviewer.Repositories;

import com.example.repoviewer.Responses.RepoResponse;

import java.util.List;

public interface IRepoRepository {
    List<RepoResponse> getRepositoriesByUsername(String username);
    String getMostUsedLanguage(String languagesUrl);
}
