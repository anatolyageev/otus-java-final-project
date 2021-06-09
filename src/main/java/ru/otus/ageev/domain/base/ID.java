package ru.otus.ageev.domain.base;

import java.io.Serializable;

/**
 * Abstract class for ID ;
 *
 * @author A.Ageev
 */
public abstract class ID<ID> implements Serializable {
    private static final long serialVersionUID = 8466257860808346236L;
    protected ID id;

    public ID() {
    }

    public ID(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
