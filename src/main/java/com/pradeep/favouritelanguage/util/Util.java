package com.pradeep.favouritelanguage.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.pradeep.favouritelanguage.datamodel.Repository;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Pmuruge on 12/8/2015.
 */
public class Util {
    public static String getGitHubUserRepoUrl(String username) {
        return String.format(Constants.GIT_HUB_API_BASE_URL + Constants.GIT_HUB_USER_REPO_URL, username);
    }


    public static ArrayList<Repository> getReposFromJson(String json) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Repository.class));
    }
}
