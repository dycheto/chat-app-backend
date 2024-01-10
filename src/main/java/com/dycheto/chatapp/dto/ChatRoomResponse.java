package com.dycheto.chatapp.dto;

import java.util.Date;

public class ChatRoomResponse {
    private Long id;
    private String name;
    private Long ownerId;
    private Date createdAt;
    private boolean isActive;
    private boolean isPrivate;

    public ChatRoomResponse(Long id, String name, Long ownerId, Date createdAt, boolean isActive, boolean isPrivate) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.isActive = isActive;
        this.isPrivate = isPrivate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public String toString() {
        return "ChatRoomResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
