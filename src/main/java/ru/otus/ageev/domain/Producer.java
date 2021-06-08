package ru.otus.ageev.domain;

import java.util.Objects;

public class Producer extends LongID {
    private String producerName;

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(producerName, producer.producerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producerName);
    }
}
