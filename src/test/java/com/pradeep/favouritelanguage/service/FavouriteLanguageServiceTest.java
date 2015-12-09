package com.pradeep.favouritelanguage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradeep.favouritelanguage.datamodel.FavouriteLanguageResponse;
import com.pradeep.favouritelanguage.util.TestUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Pmuruge on 12/9/2015.
 */
public class FavouriteLanguageServiceTest extends JerseyTest {
    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig(FavouriteLanguageService.class);
        return config;
    }

    @Test
    public void validUserTest() throws IOException {
        Response response = target("/favouriteLanguage/pradeepmurugesan").request(MediaType.APPLICATION_JSON).get();
        String responseJson = response.readEntity(String.class);
        FavouriteLanguageResponse favouriteLanguageResponse = TestUtils.jsonToObject(responseJson, FavouriteLanguageResponse.class);
        Assert.assertNull(favouriteLanguageResponse.getErrorMessage());
    }

    @Test
    public void invalidUserTest() throws IOException {
        Response response = target("/favouriteLanguage/invalidGitUser").request(MediaType.APPLICATION_JSON).get();
        String responseJson = response.readEntity(String.class);
        FavouriteLanguageResponse favouriteLanguageResponse = TestUtils.jsonToObject(responseJson, FavouriteLanguageResponse.class);
        Assert.assertNotNull(favouriteLanguageResponse.getErrorMessage());
    }

    @Test
    public void nullLanguageTest() throws IOException {
        Response response = target("/favouriteLanguage/sapna14").request(MediaType.APPLICATION_JSON).get();
        String responseJson = response.readEntity(String.class);
        FavouriteLanguageResponse favouriteLanguageResponse = TestUtils.jsonToObject(responseJson, FavouriteLanguageResponse.class);
        Assert.assertNotNull(favouriteLanguageResponse.getErrorMessage());
    }

    @Test
    public void emptyReposUserTest() throws IOException {
        Response response = target("/favouriteLanguage/pradeepmuru").request(MediaType.APPLICATION_JSON).get();
        String responseJson = response.readEntity(String.class);
        FavouriteLanguageResponse favouriteLanguageResponse = TestUtils.jsonToObject(responseJson, FavouriteLanguageResponse.class);
        Assert.assertNotNull(favouriteLanguageResponse.getErrorMessage());
    }
}
