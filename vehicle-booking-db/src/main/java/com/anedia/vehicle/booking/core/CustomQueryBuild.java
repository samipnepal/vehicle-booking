package com.anedia.vehicle.booking.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomQueryBuild implements Serializable {

    private static final long serialVersionUID = 1292698055339372912L;

    private String expression;
    private List<Object> values;
    private int limit;
    private int offset;

    public CustomQueryBuild(final String expression, final List<Object> values) {
        this.expression = expression;
        this.values = values;
    }

    public CustomQueryBuild(final String expression, final Object value) {
        this(expression, value == null ? Collections.emptyList() : Arrays.asList(value));
    }

    public CustomQueryBuild(final String expression, final List<Object> values, final int limit, final int offset) {
        this(expression,values);
        this.limit = limit;
        this.offset = offset;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("CustomQueryBuild [expression=").append(expression)
                .append(", values=").append(values)
                .append(", limit=").append(limit)
                .append(", offset=").append(offset)
                .toString();
    }
}
