package com.iiht.capsule.taskmanager.controller;

import com.iiht.capsule.taskmanager.entity.Task;
import com.iiht.capsule.taskmanager.model.ParentTaskDto;
import com.iiht.capsule.taskmanager.model.TaskDto;
import com.iiht.capsule.taskmanager.service.ParentTaskService;
import com.iiht.capsule.taskmanager.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/taskmanager")
@CrossOrigin(origins = "http://localhost:3100")
public class TaskManagerController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ParentTaskService parentTaskService;


    @PostMapping("/task/add")
    public ResponseEntity<Object> addTask(@RequestBody TaskDto taskDto) {
        if (taskDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {

            TaskDto updatedTaskDto = taskService.getTaskDtoFromTaskEntity(
            								taskService.addTask(
            										taskService.getTaskEntityFromTaskDto(taskDto)
            										));
            return new ResponseEntity<>(updatedTaskDto, HttpStatus.CREATED);
        }

    }

    @GetMapping("/task")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks().stream().map(task -> taskService.getTaskDtoFromTaskEntity(task)).collect(Collectors.toList());
    }

    @GetMapping("/parenttask")
    public List<ParentTaskDto> getAllParentTasks() {
        return parentTaskService.getAllParentTasks().stream().map(parentTask -> parentTaskService.getParentTaskDtoFromParentTaskEntity(parentTask)).collect(Collectors.toList());
    }

    @PutMapping("/task/update")
    public ResponseEntity<Object> updateTask(@RequestBody TaskDto taskDto) {
        if (taskDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Task t = taskService.getTaskById(taskDto.getTaskId());
            t.setTaskId(taskDto.getTaskId());
            t.setTask(taskDto.getTask());
            t.setPriority(taskDto.getPriority());
            t.setParentTask(parentTaskService.getParentTaskByParentId(taskDto.getParentId()));
            t.setStartDate(taskDto.getStartDate());
            t.setEndDate(taskDto.getEndDate());
            TaskDto updatedTaskDto = taskService.getTaskDtoFromTaskEntity(taskService.updateTask(t));
            return new ResponseEntity<>(updatedTaskDto, HttpStatus.OK);
        }

    }

    @PutMapping("/task/end")
    public ResponseEntity<Object> endTask(@RequestBody TaskDto taskDto) {
        if (taskDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Task task = taskService.getTaskById(taskDto.getTaskId());
            task.setTaskId(taskDto.getTaskId());
            task.setTask(taskDto.getTask());
            task.setPriority(taskDto.getPriority());
            task.setParentTask(parentTaskService.getParentTaskByParentId(taskDto.getParentId()));
            task.setStartDate(taskDto.getStartDate());
            task.setEndDate(taskDto.getEndDate());
            task.setInProgress(false);
            TaskDto updatedTaskDto = taskService.getTaskDtoFromTaskEntity(taskService.updateTask(task));
            return new ResponseEntity<>(updatedTaskDto, HttpStatus.OK);
        }
    }

}
