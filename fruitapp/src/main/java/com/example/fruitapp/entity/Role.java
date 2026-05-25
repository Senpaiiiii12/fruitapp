package com.example.fruitapp.entity;

import java.util.Set;

public enum Role {

    ADMIN(
            Set.of(
                    Permission.VIEW_FRUIT,
                    Permission.CREATE_FRUIT,
                    Permission.UPDATE_FRUIT,
                    Permission.DELETE_FRUIT,
                    Permission.MANAGE_USER
            )
    ),

    USER(
            Set.of(
                    Permission.VIEW_FRUIT
            )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {

        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {

        return permissions;
    }
}

