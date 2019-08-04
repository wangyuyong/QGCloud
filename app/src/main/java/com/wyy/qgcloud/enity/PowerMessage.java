package com.wyy.qgcloud.enity;

import java.util.List;

public class PowerMessage {
    public static final int CONST_CAN_READ = 2;

    public static final int CONST_CAN_WRITE = 1;

    /**
     * 权限类型
     */
    private int authority;

    private int position;

    private List<Integer> toId;

    public PowerMessage(int authority, List<Integer> toId,int position) {
        this.authority = authority;
        this.toId = toId;
        this.position = position;
    }

    public PowerMessage() {
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public List<Integer> getToId() {
        return toId;
    }

    public void setToId(List<Integer> toId) {
        this.toId = toId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
