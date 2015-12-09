package com.pradeep.favouritelanguage.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Pmuruge on 12/9/2015.
 */
public class TestUtils {
    public static <T> T jsonToObject(String json, Class<T> klass) throws JsonParseException, JsonMappingException, IOException
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) mapper.readValue(json, klass);
    }
}
