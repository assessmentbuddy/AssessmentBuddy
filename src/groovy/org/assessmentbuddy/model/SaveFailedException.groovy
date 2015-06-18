package org.assessmentbuddy.model

class SaveFailedException extends BaseException {
    Object bean
    
    SaveFailedException(String msg, Object bean) {
        super(msg)
        this.bean = bean
    }
    
    Object getBean() {
        return bean
    }
}
