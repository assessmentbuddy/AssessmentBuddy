// AssessmentBuddy JavaScript functions.

window.ab = (function($) {
    // Init function.
    function init() {
        // Focus on the default element (if there is one).
        $("#focuselt").focus();
    }
    
    // For creating/editing a target.
    // Update the achievement level selections based on the
    // specified array of AchievementLevel objects.
    function setAchievementLevels(achievementLevels) {
        var select = $("#achievementLevelId");
        // Remove all options but first
        select.find("option:gt(0)").remove();
        // Add options for achievement levels
        achievementLevels.forEach(function(val, index, arr) {
            console.log("appending id=" + val.id + ",name=" + val.name);
            select.append($("<option></option>").attr("value", val.id).text(val.name));
        });
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
                setAchievementLevels(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Error receiving achievement levels: " + errorThrown);
            }
        });
    }
    
    // Return exported functions
    return {
        init: init,
        getAchievementLevelsForRubric: getAchievementLevelsForRubric,
        setAchievementLevels: setAchievementLevels
    };
})(jQuery);
