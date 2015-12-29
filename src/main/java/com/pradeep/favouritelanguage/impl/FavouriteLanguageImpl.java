package com.pradeep.favouritelanguage.impl;

import com.pradeep.favouritelanguage.datamodel.Repository;
import com.pradeep.favouritelanguage.exception.FavouriteLanguageException;
import com.pradeep.favouritelanguage.repository.GitHub;
import com.pradeep.favouritelanguage.repository.VersionControlSystem;
import com.pradeep.favouritelanguage.util.Constants;
import com.pradeep.favouritelanguage.util.Util;
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
    private VersionControlSystem vcs;
    private static final Logger logger = LoggerFactory.getLogger(FavouriteLanguageImpl.class);

    public FavouriteLanguageImpl(VersionControlSystem vcs) {
        this.vcs = vcs;
    }

    public List<String> getFavouriteLanguages(String username) throws FavouriteLanguageException, Exception {
        List<String> repositoryLanguages = vcs.getRepositoryLanguages(username);
        validateLanguages(repositoryLanguages);
        repositoryLanguages = Util.removeNulls((ArrayList<String>) repositoryLanguages);
        Counter counter = new Counter(repositoryLanguages);
        return counter.getMaxKeys();
    }

    private void validateLanguages(List<String> repositoryLanguages) throws FavouriteLanguageException {
        if(repositoryLanguages.size() == 0) {
            throw new FavouriteLanguageException(Constants.ERROR_EMPTY_REPO);
        }
        if(Util.isAllNull((ArrayList<String>) repositoryLanguages)) {
            throw new FavouriteLanguageException(Constants.ERROR_NULL_REPO);
        }
    }
}
