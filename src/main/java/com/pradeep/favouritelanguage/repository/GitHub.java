package com.pradeep.favouritelanguage.repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pradeep.favouritelanguage.datamodel.Repository;
import com.pradeep.favouritelanguage.exception.FavouriteLanguageException;
import com.pradeep.favouritelanguage.util.Constants;
import com.pradeep.favouritelanguage.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pmuruge on 12/8/2015.
 */
public class GitHub implements VersionControlSystem {
    private static final Logger logger = LoggerFactory.getLogger(GitHub.class);

    public List<Repository> getRepositories(String username) throws FavouriteLanguageException {
        List<Repository> repositories = new ArrayList<Repository>();
        try {
            HttpResponse<JsonNode> response = Unirest.get(Util.getGitHubUserRepoUrl(username)).asJson();
            validateResponse(response);
            repositories = Util.getReposFromJson(response.getBody().toString());
            logger.debug("The repositories fetched from git " + repositories.toString());
            return repositories;
        } catch (UnirestException | IOException e) {
            logger.error("Error while fetching the git repos " , e);
            throw new FavouriteLanguageException(e.getMessage());
        }
    }

    public void validateResponse(HttpResponse<JsonNode> response) throws FavouriteLanguageException {
        logger.info("The status from git api is " + response.getStatus());
        if(response.getStatus() == 404 ) {
            throw new FavouriteLanguageException(Constants.ERROR_USER_INVALID);
        }
    }

    public List<String> getRepositoryLanguages(String username) throws FavouriteLanguageException {
        List<Repository> repositories = this.getRepositories(username);
        List<String> repositoryLanguages = new ArrayList<String>();
        for (Repository repository : repositories) {
            repositoryLanguages.add(repository.getLanguage());
        }
        logger.debug("Languges after parsing the repo " + repositoryLanguages);
        return repositoryLanguages;
    }
}
