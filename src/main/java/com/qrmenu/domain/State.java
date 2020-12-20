package com.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;
//This enum can be used to hold state information but state is integer for now
public enum State {

    PENDING(0),
    PREPARING(1),
    DELIVERED(2);

    private int stateInt;

    State(int i) {
        this.stateInt = i;
    }

    @JsonCreator
    public static State decode(final int stateInt){
        return Stream.of(State.values()).filter(target -> target.stateInt == stateInt)
                .findFirst().orElse(null);
    }

    @JsonValue
    public int getStateInt(){
        return stateInt;
    }
}
