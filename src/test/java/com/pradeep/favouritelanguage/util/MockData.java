package com.pradeep.favouritelanguage.util;

import com.pradeep.favouritelanguage.datamodel.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pmuruge on 12/21/2015.
 */
public class MockData {

    public static List<Repository> getValidUserRepository() {
        List<Repository> repo = new ArrayList<Repository>();
        Repository repo1 = new Repository();
        repo1.setLanguage("Java");
        Repository repo2 = new Repository();
        repo2.setLanguage("Java");
        Repository repo3 = new Repository();
        repo3.setLanguage("JavaScript");
        Repository repo4 = new Repository();
        repo4.setLanguage("Python");
        repo.add(repo1);
        repo.add(repo2);
        repo.add(repo3);
        repo.add(repo4);
        return repo;
    }

    public static List<Repository> getNullLanguageRepository() {
        List<Repository> repo = new ArrayList<Repository>();
        Repository repo1 = new Repository();
        repo1.setLanguage(null);
        Repository repo2 = new Repository();
        repo2.setLanguage(null);
        Repository repo3 = new Repository();
        repo3.setLanguage(null);
        repo.add(repo1);
        repo.add(repo2);
        repo.add(repo3);
        return repo;
    }

    public static List<Repository> getNullAndValidLanguageRepository() {
        List<Repository> repo = new ArrayList<Repository>();
        Repository repo1 = new Repository();
        repo1.setLanguage(null);
        Repository repo2 = new Repository();
        repo2.setLanguage("Java");
        Repository repo3 = new Repository();
        repo3.setLanguage(null);
        repo.add(repo1);
        repo.add(repo2);
        repo.add(repo3);
        return repo;
    }

    public static List<Repository> getMultipleFavoriteLanguageRepository() {
        List<Repository> repo = new ArrayList<Repository>();
        Repository repo1 = new Repository();
        repo1.setLanguage("Python");
        Repository repo2 = new Repository();
        repo2.setLanguage("Java");
        Repository repo3 = new Repository();
        repo3.setLanguage("Ruby");
        repo.add(repo1);
        repo.add(repo2);
        repo.add(repo3);
        return repo;
    }
}
