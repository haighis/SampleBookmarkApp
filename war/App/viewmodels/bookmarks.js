define(['durandal/plugins/router', 'services/dataservice'], function (router,dataservice) {
    var bookmarks = ko.observableArray();

    var activate = function () {
        // go get local data, if we have it
        return dataservice.getBookmarksPartials(bookmarks);
    };

    //var refresh = function () {
    //    return datacontext.getBookmarksPartials(issues, true);
    //};

    var issueadd = function () {
        router.navigateTo('#/issueadd');
    };

    //var gotoDetails = function (selectedIssue) {
    //    if (selectedIssue && selectedIssue.id()) {
    //        var url = '#/issuedetail/' + selectedIssue.id();
    //        router.navigateTo(url);
    //    }
    //};

    //var viewAttached = function (view) {
    //    bindEventToList(view, '.issue-brief', gotoDetails);
    //};

    //var bindEventToList = function (rootSelector, selector, callback, eventName) {
    //    var eName = eventName || 'click';
    //    $(rootSelector).on(eName, selector, function () {
    //        var issue = ko.dataFor(this);
    //        callback(issue);
    //        return false;
    //    });
    //};

    var vm = {
        activate: activate,
        bookmarks: bookmarks,
        title: 'Bookmarks',
        //viewAttached: viewAttached,
        issueadd: issueadd//,
        //refresh: refresh
    };

    return vm;
});