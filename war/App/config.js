define(function () {
    toastr.options.timeOut = 4000;
    toastr.options.positionClass = 'toast-bottom-right';

    var remoteServiceName = 'api/Breeze';

    var routes =
    [
       
    {
        url: 'bookmarks',
        moduleId: 'viewmodels/bookmarks',
        name: 'Bookmarks',
        visible: true,
        caption: '<i class="icon-book"></i> Bookmarks'
    }
   
    ];
    
    var startModule = 'bookmarks';

    return {
        debugEnabled: ko.observable(true),
        remoteServiceName: remoteServiceName,
        routes: routes,
        startModule: startModule
    };
});