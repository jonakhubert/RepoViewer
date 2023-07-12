package com.example.repoviewer.Services;

import com.example.repoviewer.Exceptions.UnsupportedMediaTypeException;
import com.example.repoviewer.Responses.RepoResponse;
import com.example.repoviewer.Repositories.RepoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoService {
    private final RepoRepository repoRepository;

    public RepoService(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public List<RepoResponse> getRepositoriesByUsername(String username, String acceptHeader) {
        if(acceptHeader.equals("application/xml"))
            throw new UnsupportedMediaTypeException("Media not supported: " + acceptHeader);

        return repoRepository.getRepositoriesByUsername(username);
    }
}
