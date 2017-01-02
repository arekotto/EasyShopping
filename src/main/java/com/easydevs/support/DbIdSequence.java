package com.easydevs.support;

/**
 * Created by arekotto on 29/12/2016.
 */
public class DbIdSequence {
    private Long current = 0L;

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }
}
