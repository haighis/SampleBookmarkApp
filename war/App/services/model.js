define(['config', 'durandal/system', 'services/logger'],
    function (config, system, logger) {
    
    var BookmarksPartial = function (dto) {
        // Map to observables and add computeds
        return mapToObservable(dto);
    };

    var model = {
        BookmarksPartial: BookmarksPartial
    };

    return model;

    //#region Internal Methods
    function mapToObservable(dto) {
        var mapped = {};
        for (prop in dto) {
            if (dto.hasOwnProperty(prop)) {
                mapped[prop] = ko.observable(dto[prop]);
            }
        }
        return mapped;
    };

    //function addSpeakerPartialComputeds(entity) {
    //    entity.fullName = ko.computed(function () {
    //        return entity.firstName() + ' '
    //            + entity.lastName();
    //    });
    //    entity.imageName = ko.computed(function () {
    //        return makeImageName(entity.imageSource());
    //    });
    //    return entity;
    //};
        
    function log(msg, data, showToast) {
        logger.log(msg, data, system.getModuleId(model), showToast);
    }
    //#endregion
});