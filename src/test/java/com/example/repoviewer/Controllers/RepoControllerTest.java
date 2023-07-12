package com.example.repoviewer.Controllers;

import com.example.repoviewer.Responses.RepoResponse;
import com.example.repoviewer.Services.RepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RepoControllerTest {

    private RepoService repoService;
    private RepoController underTest;

    @BeforeEach
    public void setUp() {
        repoService = mock(RepoService.class);
        underTest = new RepoController(repoService);
    }

    @Test
    public void getRepositoriesByUsername_ExistingUser_ReturnRepositories() {
        // arrange

        String username = "user";
        String acceptHeader = "application/json";

        List<RepoResponse> expected = List.of(
                new RepoResponse("repo1", "Java", "public", "https://api.gihub.com/repo1"),
                new RepoResponse("repo2", "Java", "public", "https://api.gihub.com/repo2")
        );

        when(repoService.getRepositoriesByUsername(username, acceptHeader)).thenReturn(expected);

        // act

        ResponseEntity<List<RepoResponse>> actual = underTest.getRepositoriesByUsername(username, acceptHeader);

        // assert

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    public void getRepositoriesByUsername_NonExistingUser_ThrowHttpClientErrorExceptionNotFoundException() {
        // arrange

        String username = "non_existing_user";
        String acceptHeader = "application/json";

        when(repoService.getRepositoriesByUsername(username, acceptHeader)).thenThrow(HttpClientErrorException.NotFound.class);

        // act and assert

        assertThrows(HttpClientErrorException.NotFound.class, () -> underTest.getRepositoriesByUsername(username, acceptHeader));
    }
}