package com.iiht.capsule.taskmanager.service;

import com.iiht.capsule.taskmanager.entity.Task;
import com.iiht.capsule.taskmanager.model.TaskDto;
import com.iiht.capsule.taskmanager.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService  {

	@Autowired
    private TaskRepository taskRepository;

	@Autowired
	private ParentTaskService parentTaskService;


	@Override
	public Task addTask(Task task) {
		 return taskRepository.save(task);
	}

	@Override
	public List<Task> getAllTasks(){
		List<Task> task = taskRepository.findAll();
		return task;
	}
	
	@Override
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(Long id) {
		return  taskRepository.findByTaskId(id);
	}

	@Override
	public Task getTaskEntityFromTaskDto(TaskDto taskDto)
	{
		Task task = new Task();
		task.setInProgress(true);
		task.setEndDate(taskDto.getEndDate());
		task.setParentTask(parentTaskService.getParentTaskByParentId(taskDto.getParentId()));
		task.setPriority(taskDto.getPriority());
		task.setStartDate(taskDto.getStartDate());
		task.setTask(taskDto.getTask());
		task.setTaskId(taskDto.getTaskId());

		return task;
	}

	@Override
	public TaskDto getTaskDtoFromTaskEntity(Task task)
	{
		TaskDto taskDto = new TaskDto();
		taskDto.setInProgress(task.isInProgress());
		taskDto.setEndDate(task.getEndDate());
		taskDto.setParentId(task.getParentTask().getParentId());
		taskDto.setPriority(task.getPriority());
		taskDto.setStartDate(task.getStartDate());
		taskDto.setTask(task.getTask());
		taskDto.setTaskId(task.getTaskId());

		return taskDto;
	}

}
