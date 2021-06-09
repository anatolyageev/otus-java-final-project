package ru.otus.ageev.domain;

import ru.otus.ageev.domain.base.ID;

public class LongID extends ID<Long> {
    private static final long serialVersionUID = -6889036256149495388L;

    public LongID() {
    }

    public LongID(Long id) {
        super(id);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long aLong) {
        super.setId(aLong);
    }
}
