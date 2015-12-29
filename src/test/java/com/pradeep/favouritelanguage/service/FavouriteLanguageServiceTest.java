package com.pradeep.favouritelanguage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradeep.favouritelanguage.datamodel.FavouriteLanguageResponse;
import com.pradeep.favouritelanguage.datamodel.Repository;
import com.pradeep.favouritelanguage.exception.FavouriteLanguageException;
import com.pradeep.favouritelanguage.impl.FavouriteLanguageImpl;
import com.pradeep.favouritelanguage.repository.GitHub;
import com.pradeep.favouritelanguage.util.Constants;
import com.pradeep.favouritelanguage.util.MockData;
import com.pradeep.favouritelanguage.util.TestUtils;
import junit.framework.TestCase;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Pmuruge on 12/9/2015.
 */
public class FavouriteLanguageServiceTest {

    @Test
    public void validUserTest() throws Exception {
        GitHub mockGit = Mockito.spy(GitHub.class);
        Mockito.doReturn(MockData.getValidUserRepository()).when(mockGit).getRepositories("username");
        List<String> languages = new FavouriteLanguageImpl(mockGit).getFavouriteLanguages("username");
        Assert.assertEquals(languages.get(0), "Java");
    }

    @Test
    public void emptyReposUserTest() {

        GitHub mockGit = Mockito.spy(GitHub.class);
        try {
            Mockito.doReturn(new ArrayList<Repository>()).when(mockGit).getRepositories("username");
            List<String> languages = new FavouriteLanguageImpl(mockGit).getFavouriteLanguages("username");
            TestCase.fail("The user has no repos associated test failed");
        } catch (Exception e) {
            Assert.assertEquals(Constants.ERROR_EMPTY_REPO, e.getMessage());
        }
    }

    @Test
    public void nullLanguageTest() {
        GitHub mockGit = Mockito.spy(GitHub.class);
        try {
            Mockito.doReturn(MockData.getNullLanguageRepository()).when(mockGit).getRepositories("username");
            List<String> languages = new FavouriteLanguageImpl(mockGit).getFavouriteLanguages("username");
            TestCase.fail("The user has one or more repos with no language specified test failed");
        } catch (Exception e) {
            Assert.assertEquals(Constants.ERROR_NULL_REPO, e.getMessage());
        }
    }

    @Test
    public void validAndNullLanguageTest() throws Exception {
        GitHub mockGit = Mockito.spy(GitHub.class);
        Mockito.doReturn(MockData.getNullAndValidLanguageRepository()).when(mockGit).getRepositories("nullAndValidLangUser");
        List<String> languages = new FavouriteLanguageImpl(mockGit).getFavouriteLanguages("nullAndValidLangUser");
        Assert.assertEquals(languages.get(0), "Java");
    }

    @Test
    public void validMultipleLanguageTest() throws Exception {
        GitHub mockGit = Mockito.spy(GitHub.class);
        Mockito.doReturn(MockData.getMultipleFavoriteLanguageRepository()).when(mockGit).getRepositories("username");
        List<String> languages = new FavouriteLanguageImpl(mockGit).getFavouriteLanguages("username");
        Assert.assertTrue(languages.containsAll(TestUtils.getExpectedMultipleFavLanguages()));
    }

    @Test
    public void invalidUserTest() throws IOException {
        GitHub mockGit = Mockito.spy(GitHub.class);
        try {
            Mockito.doThrow(new FavouriteLanguageException("User Name is Invalid")).when(mockGit).getRepositories("username");
            List<String> languages = new FavouriteLanguageImpl(mockGit).getFavouriteLanguages("username");
            TestCase.fail("User Name is Invalid test failed");
        } catch (Exception e) {
            Assert.assertEquals(Constants.ERROR_USER_INVALID, e.getMessage());
        }
    }
}
