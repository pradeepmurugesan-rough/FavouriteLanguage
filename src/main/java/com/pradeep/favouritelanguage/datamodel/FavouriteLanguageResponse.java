package com.pradeep.favouritelanguage.datamodel;

import java.util.List;

/**
 * Created by Pmuruge on 12/9/2015.
 */
public class FavouriteLanguageResponse {
    private List<String> languages;
    private String errorMessage;
    public FavouriteLanguageResponse(){}

    public FavouriteLanguageResponse(List<String> languages, String errorMessage) {
        this.languages = languages;
        this.errorMessage = errorMessage;
    }
    public List<String> getLanguages() {
        return languages;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

}
