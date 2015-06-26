// AssessmentBuddy JavaScript functions.

window.ab = (function($) {
    // Init function.
    function init() {
        // Focus on the default element (if there is one).
        $("#focuselt").focus();
    }
    
    // For creating/editing a target.
    // When a rubric is selected, allow the user to select
    // the achievement levels for that rubric.
    function getAchievementLevelsForRubric(ajaxUrl) {
        var rubricId = $("#rubricId").val();
        console.log("Get achievement levels for rubric id=" + rubricId);
        $.ajax({
            url: ajaxUrl + '?id=' + rubricId,
            dataType: 'json',
            success: function(data, status, jqXHR) {
                console.log("Received " + data.length + " achievement level(s)");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Error receiving achievement levels: " + errorThrown);
            }
        });
    }
    
    // Return exported functions
    return {
        init: init,
        getAchievementLevelsForRubric: getAchievementLevelsForRubric
    };
})(jQuery);
