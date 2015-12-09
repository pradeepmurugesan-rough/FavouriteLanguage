package com.pradeep.favouritelanguage.impl;

import com.pradeep.favouritelanguage.datamodel.Repository;
import com.pradeep.favouritelanguage.exception.FavouriteLanguageException;
import com.pradeep.favouritelanguage.repository.GitHub;
import com.pradeep.favouritelanguage.repository.VersionControlSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by Pmuruge on 12/8/2015.
 */
public class FavouriteLanguageImpl {
    private Integer maxCount;
    private HashMap<String, Integer> counter;
    private List<String> favouriteLanguages;
    private VersionControlSystem vcs;
    private static final Logger logger = LoggerFactory.getLogger(FavouriteLanguageImpl.class);

    public FavouriteLanguageImpl() {
        counter = new HashMap<String, Integer>();
        maxCount = 0;
        favouriteLanguages = new ArrayList<String>();
        vcs = new GitHub();
    }

    public List<String> getFavouriteLanguages(String username) throws FavouriteLanguageException {
        List<Repository> repositories = vcs.getRepositories(username);
        counter = countLanguages(repositories);
        List<String> topRepos =  getTopLanguages();
        validateRepos(topRepos);
        return topRepos;
    }

    private void validateRepos(List<String> topRepos) throws FavouriteLanguageException {
        if(topRepos.size() == 0) {
            throw new FavouriteLanguageException("The user has no repo with Languages");
        }
        if(topRepos.contains(null)) {
            throw new FavouriteLanguageException("The user has one more repos with null as Language");
        }
    }

    public HashMap<String, Integer> countLanguages(List<Repository> repositories) {
        for (Repository repository : repositories) {
            String repoLanguage = repository.getLanguage();
            incrementCount(repoLanguage);
            setMax(repoLanguage);
        }
        logger.debug("The repository counter is " + counter.toString());
        return counter;
    }

    private void setMax(String repoLanguage) {
        if(counter.get(repoLanguage) > maxCount) {
            maxCount = counter.get(repoLanguage);
        }
    }

    private void incrementCount(String repoLanguage) {
        if(counter.containsKey(repoLanguage)) {
            counter.put(repoLanguage, counter.get(repoLanguage) + 1);
        } else {
            counter.put(repoLanguage, 1);
        }
    }

    private List<String> getTopLanguages() {
        for (Entry<String, Integer> entry : counter.entrySet()) {
            if(entry.getValue() == maxCount) {
                favouriteLanguages.add(entry.getKey());
            }
        }
        return favouriteLanguages;
    }
}
