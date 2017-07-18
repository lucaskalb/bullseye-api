package com.lucask.bullseyes.infrastruture;

public interface Mapper<S, D> {

    public D map(S source);
}
