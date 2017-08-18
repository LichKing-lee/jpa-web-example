package com.yong.domain;

public enum DeliveryStatus {
    COMPLETE, CANCEL;

    public boolean isComplete(){
        return this == COMPLETE;
    }
}
