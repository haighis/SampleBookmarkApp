package restful.server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import restful.server.dao.BookmarkDao;
import restful.server.dao.CategoryDao;
import restful.server.domain.Bookmark;
import restful.server.domain.Category;
import restful.server.resource.BookmarkResource;
import restful.server.resource.CategoryResource;
import restful.server.resource.HelloResource;

public class RestfulApplication extends Application{
	 
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public RestfulApplication() {
		
		/*
		CategoryDao categoryDao = new CategoryDao();
		//if(categoryDao.countAll() <= 0) {
			Category category = null;
			for(int i = 1; i < 10; i++) {
				category = new Category();
				category.setId(i);
				category.setName("Category "+i);
				category.setDescription("Desc " + i);
				categoryDao.put(category);
			}
		//}
		
			BookmarkDao bookmarkDao = new BookmarkDao();
			//if(categoryDao.countAll() <= 0) {
				Bookmark b = null;
				for(int i = 1; i < 20; i++) {
					b = new Bookmark();
					b.setId(i);
					b.setTitle("Bookmark "+i);
					b.setDescription("Desc " + i);
					b.setUrl("http://" + i + ".com");
					bookmarkDao.put(b);
				}	
		*/
		
		singletons.add(new HelloResource());
		singletons.add(new CategoryResource());
		singletons.add(new BookmarkResource());
	}
    
    @Override
    public Set<Class<?>> getClasses() {
    	return empty;
    }
    
    @Override
    public Set<Object> getSingletons() {
    	return singletons;
    }
}
