package com.example.repoviewer.Repositories;

import com.example.repoviewer.Models.Repo;
import com.example.repoviewer.Responses.RepoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepoRepositoryTest {

    private RestTemplate restTemplate;
    private RepoRepository underTest;

    @BeforeEach
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        underTest = new RepoRepository(restTemplate);
    }

    @Test
    public void getRepositoriesByUsername_ExistingUser_ReturnRepositories() {
        // arrange

        Repo[] repos = new Repo[] {
                new Repo("repo1", "public", "https://api.github.com/repo1/languages", "https://api.gihub.com/repo1"),
                new Repo("repo2", "public", "https://api.github.com/repo2/languages", "https://api.gihub.com/repo2")
        };

        List<RepoResponse> expected = Arrays.asList(
                new RepoResponse("repo1", "Java", "public", "https://api.gihub.com/repo1"),
                new RepoResponse("repo2", "Java", "public", "https://api.gihub.com/repo2")
        );

        when(restTemplate.getForObject(anyString(), any())).thenReturn(repos);
        when(restTemplate.exchange(any(RequestEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(getLanguageResponse(), HttpStatus.OK));

        // act

        List<RepoResponse> actual = underTest.getRepositoriesByUsername("user");

        // assert

        assertEquals(expected, actual);
    }

    @Test
    public void getRepositoriesByUsername_ExistingUserWithNoRepositories_ReturnEmptyList() {
        // arrange

        Repo[] repos = new Repo[0];

        when(restTemplate.getForObject(anyString(), any())).thenReturn(repos);
        when(restTemplate.exchange(any(RequestEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(getLanguageResponse(), HttpStatus.OK));

        // act

        List<RepoResponse> actual = underTest.getRepositoriesByUsername("user");

        // assert

        assertEquals(Collections.emptyList(), actual);
    }

    private Map<String, Integer> getLanguageResponse() {
        Map<String, Integer> response = new HashMap<>();
        response.put("Java", 100);
        response.put("Python", 50);
        return response;
    }
}