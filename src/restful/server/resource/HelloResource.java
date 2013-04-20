package restful.server.resource;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import restful.server.dao.BookmarkDao;
import restful.server.domain.Bookmark;
import restful.server.domain.Category;

@Path("/add")
public class HelloResource {
		
	private Logger logger = Logger.getLogger(BookmarkResource.class.toString());
	
	BookmarkDao bookmarkDao;
	
	public HelloResource() {
		bookmarkDao = new BookmarkDao();
	}
	
/*	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello() {
		String output = "Say hello world via response";
		ResponseBuilder responseBuilder = Response.ok(output);
		responseBuilder.language("fr")
					   .header("varmansvn", "a man of nowhere");
		return responseBuilder.build();
	}
	
	@GET
	@Path("{id}")
	@Produces({"application/xml","application/json"})
	public Category get(@PathParam("id") String id) {
		Category category = new Category();
		category.setId(Long.parseLong(id));
		category.setName("Tablet");
		return category;
	}
**/
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Bookmark /**Response**/ post(Bookmark bookmark, @Context UriInfo uriInfo) {
		//logger.info("requested uri: "+uriInfo.getAbsolutePath());
		//logger.info("received bookmark: "+bookmark.toString());
		Bookmark newBookmark = null;
		
		newBookmark = new Bookmark();
		
		newBookmark.setTitle(bookmark.getTitle());
		newBookmark.setUrl(bookmark.getUrl());
		newBookmark.setDescription(bookmark.getDescription());
		//logger.info("persist new bookmark: "+newBookmark.toString());
		bookmarkDao.put(newBookmark);
		//return Response.created(URI.create("/bookmarks/" + newBookmark.getId())).header("Access-Control-Allow-Origin", "*").build();
		return newBookmark;
	}
} 