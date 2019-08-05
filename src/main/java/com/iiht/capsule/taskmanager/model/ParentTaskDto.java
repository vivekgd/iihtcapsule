package com.iiht.capsule.taskmanager.model;

public class ParentTaskDto {

    private Long parentId;
    private String parentTask;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }

    @Override
    public String toString() {
        return "ParentTaskDto{" +
                "parentId=" + parentId +
                ", parentTask='" + parentTask + '\'' +
                '}';
    }
}
