package com.solvd.nav.classes;

public class RefWeight {
    private final float distance;
    private final float time;

    public RefWeight(float distance, float time) {
        this.distance = distance;
        this.time = time;
    }

    public RefWeight() {
        this.distance = 0f;
        this.time = 0f;
    }

    public float distance() {
        return distance;
    }

    public float time() {
        return time;
    }

    public boolean isMAXDistance() {
        return this.distance == Float.MAX_VALUE;
    }
    public boolean isMAXTime() {
        return this.distance == Float.MAX_VALUE;
    }
}
