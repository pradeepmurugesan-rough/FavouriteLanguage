package com.pradeep.favouritelanguage.repository;

import com.pradeep.favouritelanguage.datamodel.Repository;
import com.pradeep.favouritelanguage.exception.FavouriteLanguageException;

import java.util.List;

/**
 * Created by Pmuruge on 12/8/2015.
 */
public interface VersionControlSystem {
    public List<Repository> getRepositories(String username) throws FavouriteLanguageException;
    public List<String> getRepositoryLanguages(String username) throws FavouriteLanguageException ;
}
