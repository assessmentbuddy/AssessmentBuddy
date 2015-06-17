package org.assessmentbuddy

class UITagLib {
    static namespace = "ab"
    
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    
    static defaultEncodeAs = "raw"
    
    def conditionalLink = { attrs, body ->
        def enabled = attrs.enabled
        println "enabled=${enabled}"
        def renderedBody = body()
        if (enabled) {
            out << g.link(attrs) { renderedBody }
        } else {
            out << "<div class='disabledbtnlink'>" << renderedBody << "</div>"
        }
    }
}
