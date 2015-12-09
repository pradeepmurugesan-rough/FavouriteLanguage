package com.pradeep.favouritelanguage.service;

import com.pradeep.favouritelanguage.datamodel.FavouriteLanguageResponse;
import com.pradeep.favouritelanguage.exception.FavouriteLanguageException;
import com.pradeep.favouritelanguage.impl.FavouriteLanguageImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Pmuruge on 12/8/2015.
 */
@Path("/favouriteLanguage")
@Produces({ "application/json" })
public class FavouriteLanguageService {

    private static final Logger logger = LoggerFactory.getLogger(FavouriteLanguageService.class);
    @GET
    @Path("/{user}")
    public Response getFavouriteLanguage(@PathParam("user") String user) {
        try {
            List<String> repos = new FavouriteLanguageImpl().getFavouriteLanguages(user);
            return Response.ok(new FavouriteLanguageResponse(repos, null)).build();
        } catch (FavouriteLanguageException e) {
            return Response.ok(new FavouriteLanguageResponse(null, e.getMessage())).build();
        } catch (Exception e) {
            return Response.serverError().entity(new FavouriteLanguageResponse(null, e.getMessage())).build();
        }

    }
}
