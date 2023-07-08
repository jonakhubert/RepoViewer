package com.example.repoviewer.Services;

import com.example.repoviewer.Models.Exceptions.UnsupportedMediaType;
import com.example.repoviewer.Models.Responses.RepoResponse;
import com.example.repoviewer.Repositories.RepoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoService {
    private final RepoRepository repoRepository;

    public RepoService(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public List<RepoResponse> getRepositories(String username, String acceptHeader) {
        if(acceptHeader.contains("application/xml"))
            throw new UnsupportedMediaType();

        return repoRepository.getRepositoriesByUsername(username);
    }
}
