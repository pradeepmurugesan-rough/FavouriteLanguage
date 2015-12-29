package com.pradeep.favouritelanguage.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradeep.favouritelanguage.datamodel.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pmuruge on 12/9/2015.
 */
public class TestUtils {
    public static final String validUser = "pradeepmurugesan";
    public static final String inValidUser = "gitinvaliduers";
    public static final String emptyRepoUser = "pradeepmuru";
    public static final String nullRepoUser = "sapna14";

    public static <T> T jsonToObject(String json, Class<T> klass) throws JsonParseException, JsonMappingException, IOException
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) mapper.readValue(json, klass);
    }

    public static List<String> getExpectedMultipleFavLanguages() {
        List<String> expectedLanguages = new ArrayList<String>();
        expectedLanguages.add("Python");
        expectedLanguages.add("Java");
        expectedLanguages.add("Ruby");
        return expectedLanguages;
    }
}
