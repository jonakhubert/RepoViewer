package com.example.repoviewer.Services;

import com.example.repoviewer.Exceptions.UnsupportedMediaTypeException;
import com.example.repoviewer.Repositories.RepoRepository;
import com.example.repoviewer.Responses.RepoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RepoServiceTest {

    private RepoRepository repoRepository;
    private RepoService underTest;

    @BeforeEach
    public void setUp() {
        repoRepository = mock(RepoRepository.class);
        underTest = new RepoService(repoRepository);
    }

    @Test
    public void getRepositoriesByUsername_ValidAcceptHeader_ReturnRepositories() {
        // arrange

        String username = "user";
        String acceptHeader = "application/json";

        List<RepoResponse> expected = List.of(
                new RepoResponse("repo1", "Java", "public", "https://api.gihub.com/repo1"),
                new RepoResponse("repo2", "Java", "public", "https://api.gihub.com/repo2")
        );

        when(repoRepository.getRepositoriesByUsername(username)).thenReturn(expected);

        // act

        List<RepoResponse> actual = underTest.getRepositoriesByUsername(username, acceptHeader);

        // assert

        assertEquals(expected, actual);
    }

    @Test
    public void getRepositoriesByUsername_InvalidAcceptHeader_ThrowUnsupportedMediaTypeException() {
        // arrange

        String username = "user";
        String acceptHeader = "application/xml";

        // act and assert

        assertThrows(UnsupportedMediaTypeException.class, () -> underTest.getRepositoriesByUsername(username, acceptHeader));
    }
}