define(['services/logger', 'durandal/system', 'services/model'],
    function (logger, system, model) {

	 var options = {
             url: '/rest/bookmarks',
             type: 'GET',
             dataType: 'json'
         };
	
        var getBookmarksPartials = function (bookmarksObservable) {
            // reset the observable
            bookmarksObservable([]);

            // set ajax call
           

            // make call
            return $.ajax(options)
                .then(querySucceeded)
                .fail(queryFailed);

            // handle the ajax callback
            function querySucceeded(data) {
                var bookmarks = [];
                // data.sort(sortSpeakers);
                
             // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
            	var list = data.bookmark == null ? [] : (data.bookmark instanceof Array ? data.bookmark : [data.bookmark]);

            	//$('#wineList li').remove();
            	$.each(list, function(index, item) {
            		var s = new model.BookmarksPartial(item);
                    bookmarks.push(s);
            		//var bookmarkitem = item.title;
            	});
                
                
                
                //data.bookmark.forEach(function (item) {
                    //var s = new model.BookmarksPartial(item);
                    //bookmarks.push(s);
                //});
                
                bookmarksObservable(bookmarks);
                
                log('Retrieved bookmarks partials from remote data source',               		bookmarks,                    true);
            }
        };

        var getBookmarkById = function (bookmarkId, bookmarkObservable) {
            // reset the observable
            bookmarkObservable([]);

            // make call
            return $.ajax(options)
                .then(querySucceeded)
                .fail(queryFailed);

            // handle the ajax callback
            function querySucceeded(data) {
                var bookmark = [];
                // data.sort(sortSpeakers);
                
             // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
            	var list = data.bookmark == null ? [] : (data.bookmark instanceof Array ? data.bookmark : [data.bookmark]);

            	//$('#wineList li').remove();
            	$.each(list, function(index, item) {
            		var s = new model.BookmarksPartial(item);
                    bookmarks.push(s);
            		//var bookmarkitem = item.title;
            	});
                
                
                
                //data.bookmark.forEach(function (item) {
                    //var s = new model.BookmarksPartial(item);
                    //bookmarks.push(s);
                //});
                
                bookmarksObservable(bookmarks);
                
                log('Retrieved bookmark by id from remote data source',               		bookmarks,                    true);
            }
        };
        
        var dataservice = {
            getBookmarksPartials: getBookmarksPartials,
            getBookmarkById:getBookmarkById
        };
        return dataservice;

        //#region Internal methods
        //function sortSpeakers(s1, s2) {
        //    return (s1.firstName + s1.lastName > s2.firstName + s2.lastName)
        //        ? 1 : -1;
        //}


        function queryFailed(jqXHR, textStatus) {
            var msg = 'Error getting data. ' + textStatus;
            logger.log(msg,
                jqXHR,
                system.getModuleId(dataservice),
                true);
        }

        function log(msg, data, showToast) {
            logger.log(msg,
                data,
                system.getModuleId(dataservice),
                showToast);
        }
        //#endregion

    });