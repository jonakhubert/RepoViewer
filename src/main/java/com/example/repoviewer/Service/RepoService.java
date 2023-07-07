package com.example.repoviewer.Service;

import com.example.repoviewer.Model.Response.RepoResponse;
import com.example.repoviewer.Repository.RepoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoService {
    private final RepoRepository repoRepository;

    public RepoService(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public List<RepoResponse> getRepositories(String username) {
        return repoRepository.getRepositoriesByUsername(username);
    }
}
