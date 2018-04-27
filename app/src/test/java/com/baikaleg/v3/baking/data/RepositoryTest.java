package com.baikaleg.v3.baking.data;

import com.baikaleg.v3.baking.data.network.RecipeApi;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RepositoryTest {
    @Mock
    RecipeApi recipeApi;

    private Repository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = new Repository(recipeApi);
    }
}