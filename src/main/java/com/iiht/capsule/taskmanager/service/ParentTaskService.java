package com.iiht.capsule.taskmanager.service;

import com.iiht.capsule.taskmanager.entity.ParentTask;
import com.iiht.capsule.taskmanager.model.ParentTaskDto;

import java.util.List;

public interface ParentTaskService {
    List<ParentTask> getAllParentTasks();
    ParentTask getParentTaskByParentId(Long id);
    ParentTaskDto getParentTaskDtoFromParentTaskEntity(ParentTask parentTask);
}
