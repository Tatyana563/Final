package com.home.sphibcity.dto.request;

import lombok.Getter;

@Getter
public class Pair<F, S> {

    private F id;
    private S value;

    public Pair(F id, S value) {
        this.id = id;
        this.value = value;
    }
}
