// The only thing we use JavaScript for is setting default focus.
(function() {
    // Make sure current onload function is called if there is one.
    var origOnload = window.onload;
    
    // Register onload function.
    window.onload = function() {
        //console.log("window.onload is firing");

        if (origOnload) {
            origOnload();
        }

        var focuselt = document.getElementById("focuselt");
        if (focuselt) {
            //console.log("setting focus element");
            focuselt.focus();
        }
    };
})();