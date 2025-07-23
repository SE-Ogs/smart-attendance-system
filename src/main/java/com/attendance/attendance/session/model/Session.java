package com.attendance.attendance.session.model;




public class Session {

   
    private Long id;
    private Long classId;
    private Long teacherId;
    private String code;
    private boolean isActive = true;

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public Long getId() {
    return this.id;
}

    public String getSessionCode() {
    return this.code;
}

}
