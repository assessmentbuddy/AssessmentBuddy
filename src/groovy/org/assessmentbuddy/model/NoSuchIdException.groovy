package org.assessmentbuddy.model

class NoSuchIdException extends BaseException {
    int id
    
    NoSuchIdException(String msg, int id) {
        super(msg)
        this.id = id
    }
    
    int getId() {
        return id
    }
}
