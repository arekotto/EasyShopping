package com.easydevs.support;

/**
 * Created by arekotto on 29/12/2016.
 */
public class DbIdSequence {
    private Long current = 0L;

    /**
     * Gets current.
     *
     * @return the current
     */
    public Long getCurrent() {
        return current;
    }

    /**
     * Sets current.
     *
     * @param current the current
     */
    public void setCurrent(Long current) {
        this.current = current;
    }
}
