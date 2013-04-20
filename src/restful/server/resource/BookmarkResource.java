package restful.server.resource;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;	
import javax.ws.rs.core.UriInfo;

import com.google.appengine.api.datastore.EntityNotFoundException;

import restful.server.dao.BookmarkDao;
import restful.server.domain.Bookmark;

@Path("/bookmarks")
public class BookmarkResource {
	
	private Logger logger = Logger.getLogger(BookmarkResource.class.toString());
	
	BookmarkDao bookmarkDao;
	public BookmarkResource() {
		bookmarkDao = new BookmarkDao();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Bookmark get(@PathParam("id") long id, @Context UriInfo uriInfo) {
		logger.info("requested uri: "+uriInfo.getAbsolutePath());
		Bookmark retrievedBookmark = null;
		try {
			retrievedBookmark = bookmarkDao.get(id);
			logger.info("retrieved bookmark: "+retrievedBookmark.toString());
		} catch(EntityNotFoundException enfe) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch(Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return retrievedBookmark;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Bookmark> get(@Context UriInfo uriInfo) {
		logger.info("requested uri: "+uriInfo.getAbsolutePath());
		List<Bookmark> bookmarkList;
		bookmarkList = bookmarkDao.getByRange(0, bookmarkDao.countAll());
		String strBookmarkList = "";
		for(Bookmark bookmark : bookmarkList) {
			strBookmarkList = bookmark.toString() + ", " + strBookmarkList;
		}
		logger.info("retrieved bookmark list: " + strBookmarkList);
		return bookmarkList;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Bookmark /**Response**/ post(Bookmark bookmark, @Context UriInfo uriInfo) {
		logger.info("requested uri: "+uriInfo.getAbsolutePath());
		logger.info("received bookmark: "+bookmark.toString());
		Bookmark newBookmark = null;
		//if(bookmarkDao.getByField("title", bookmark.getTitle()).iterator().hasNext()) {
		//	throw new WebApplicationException(Response.Status.CONFLICT);
		//}
		
		newBookmark = new Bookmark();
		//newBookmark.setId(bookmarkDao.countAll()+112121);
		newBookmark.setTitle(bookmark.getTitle());
		newBookmark.setUrl(bookmark.getUrl());
		newBookmark.setDescription(bookmark.getDescription());
		logger.info("persist new bookmark: "+newBookmark.toString());
		bookmarkDao.put(newBookmark);
		//return Response.created(URI.create("/bookmarks/" + newBookmark.getId())).header("Access-Control-Allow-Origin", "*").build();
		return newBookmark;
	}
	
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response put(@PathParam("id") long id, Bookmark bookmark, @Context UriInfo uriInfo) {
		logger.info("requested uri: "+uriInfo.getAbsolutePath());
		Bookmark retrievedBookmark = null;
		try {
			retrievedBookmark = bookmarkDao.get(id);
			logger.info("retrieved bookmark: "+retrievedBookmark.toString());
		} catch(EntityNotFoundException enfe) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch(Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		retrievedBookmark.setTitle(bookmark.getTitle());
		retrievedBookmark.setUrl(bookmark.getUrl());
		retrievedBookmark.setDescription(bookmark.getDescription());
		bookmarkDao.put(retrievedBookmark);
		logger.info("updated bookmark: "+retrievedBookmark.toString());
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id, @Context UriInfo uriInfo) {
		logger.info("requested uri: "+uriInfo.getAbsolutePath());
		Bookmark retrievedBookmark = null;
		try {
			retrievedBookmark = bookmarkDao.get(id);
			logger.info("retrieved bookmark: "+retrievedBookmark.toString());
		} catch(EntityNotFoundException enfe) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch(Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		bookmarkDao.delete(retrievedBookmark);
		logger.info("deleted bookmark: "+retrievedBookmark.toString());
		return Response.noContent().build();
	}
}
