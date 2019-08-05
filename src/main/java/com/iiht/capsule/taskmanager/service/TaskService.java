package com.iiht.capsule.taskmanager.service;

import com.iiht.capsule.taskmanager.entity.Task;
import com.iiht.capsule.taskmanager.model.TaskDto;

import java.util.List;

public interface TaskService  {
	Task addTask(Task task);
	List<Task> getAllTasks();
	Task updateTask(Task task);
	Task getTaskById(Long id);
	Task getTaskEntityFromTaskDto(TaskDto taskDto);
	TaskDto getTaskDtoFromTaskEntity(Task task);
}
