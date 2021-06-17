package com.github.ksgfk.teaathome.models;

public enum UserPermission {
    USER(0),
    ADMIN(1);

    int level;

    UserPermission(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
