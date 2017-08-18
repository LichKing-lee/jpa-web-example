package com.yong.domain;

public enum DeliveryStatus {
    COMPLETE;

    public boolean isComplete(){
        return this == COMPLETE;
    }
}
