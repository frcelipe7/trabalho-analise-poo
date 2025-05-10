package br.uepa.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Group {
    private final int id;
    private String name;
    private ArrayList<String> permissions;
    private LocalDateTime joinedAt;

    public Group(int id, String name, ArrayList permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public ArrayList getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(String permission) {
        return this.permissions.contains(permission);
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void addUser(User user) {
        user.setGroup_id(this.id);
        this.joinedAt = LocalDateTime.now();
    }
}
