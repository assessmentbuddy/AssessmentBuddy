package org.assessmentbuddy.model

class NoSuchIdException extends BaseException {
    long id
    
    NoSuchIdException(String msg, long id) {
        super(msg)
        this.id = id
    }
    
    long getId() {
        return id
    }
}
