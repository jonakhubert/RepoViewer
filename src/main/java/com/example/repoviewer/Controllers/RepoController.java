package com.example.repoviewer.Controllers;

import com.example.repoviewer.Models.Responses.RepoResponse;
import com.example.repoviewer.Services.RepoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repository-management")
public class RepoController {
    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<RepoResponse>> getRepositories(
            @PathVariable String username,
            @RequestHeader(name = "Accept", defaultValue = "application/json") String acceptHeader) {
        return ResponseEntity.ok(repoService.getRepositories(username, acceptHeader));
    }
}
