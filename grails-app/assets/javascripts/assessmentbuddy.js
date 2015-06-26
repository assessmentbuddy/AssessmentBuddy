// AssessmentBuddy JavaScript functions.

window.ab = (function($) {
    // Init function.
    function init() {
        // Focus on the default element (if there is one).
        $("#focuselt").focus();
    }
    
    // Return exported functions
    return {
        init: init
    };
})(jQuery);
