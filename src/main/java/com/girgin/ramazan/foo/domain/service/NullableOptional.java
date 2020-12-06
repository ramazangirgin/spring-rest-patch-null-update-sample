package com.girgin.ramazan.foo.domain.service;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class NullableOptional<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final NullableOptional<?> UNDEFINED =
            new NullableOptional<>(null, false);

    private T value;
    private boolean isPresent;

    private NullableOptional(T value, boolean isPresent) {
        this.value = value;
        this.isPresent = isPresent;
    }

    public static <T> NullableOptional<T> undefined() {
        return (NullableOptional<T>) UNDEFINED;
    }

    public static <T> NullableOptional<T> of(T value) {
        return new NullableOptional<T>(value, true);
    }

    public T get() {
        if (!isPresent) {
            throw new NoSuchElementException("Value is undefined");
        }
        return value;
    }

    public T orElse(T other) {
        return this.isPresent ? this.value : other;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void ifPresent(Consumer<? super T> action) {
        if (isDefined()) {
            action.accept(value);
        }
    }

    private boolean isDefined() {
        return !isUndefined();
    }

    private boolean isUndefined() {
        return this.equals(UNDEFINED);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NullableOptional)) {
            return false;
        }

        NullableOptional<?> other = (NullableOptional<?>) obj;
        return equals(value, other.value) &&
                equals(isPresent, other.isPresent);
    }

    private static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    @Override
    public int hashCode() {
        int result = 31 + (value == null ? 0 : value.hashCode());
        Boolean bool1 = Boolean.TRUE;
        bool1.hashCode();
        result = 31 * result + (isPresent ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return this.isPresent ? String.format("NullableOptional[%s]", value) : "NullableOptional.undefined";
    }
}
