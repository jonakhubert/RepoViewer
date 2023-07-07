package com.example.repoviewer.Controller;

import com.example.repoviewer.Model.Response.RepoResponse;
import com.example.repoviewer.Service.RepoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repository-management")
public class RepoController {
    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<RepoResponse>> getRepositories(@PathVariable String username) {
        return ResponseEntity.ok(repoService.getRepositories(username));
    }
}
