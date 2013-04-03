package restful.server.dao;

import restful.server.domain.Bookmark;

import com.googlecode.objectify.ObjectifyService;

public class BookmarkDao extends ObjectifyDao<Bookmark>{
	
	  static
	  {
	    ObjectifyService.register(Bookmark.class);
	  }

	  public BookmarkDao() {
		  
		  super(Bookmark.class);
	  
	  }
}
