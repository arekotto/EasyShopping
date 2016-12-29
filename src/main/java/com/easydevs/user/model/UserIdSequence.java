package com.easydevs.user.model;

/**
 * Created by arekotto on 29/12/2016.
 */
public class UserIdSequence {
    private Long current = 0L;

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }
}
