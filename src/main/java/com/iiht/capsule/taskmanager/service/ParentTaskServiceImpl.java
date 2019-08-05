package com.iiht.capsule.taskmanager.service;

import com.iiht.capsule.taskmanager.entity.ParentTask;
import com.iiht.capsule.taskmanager.model.ParentTaskDto;
import com.iiht.capsule.taskmanager.repository.ParentTaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentTaskServiceImpl implements ParentTaskService {

    @Autowired
    ParentTaskRepository parentTaskRepository;

    @Override
    public List<ParentTask> getAllParentTasks() {
        return parentTaskRepository.findAll();
    }

    @Override
    public ParentTask getParentTaskByParentId(Long id) {
        return parentTaskRepository.findByParentId(id);
    }

    @Override
    public ParentTaskDto getParentTaskDtoFromParentTaskEntity(ParentTask parentTask)
    {
        ParentTaskDto parentTaskDto = new ParentTaskDto();
        parentTaskDto.setParentId(parentTask.getParentId());
        parentTaskDto.setParentTask(parentTask.getParentTask());
        return parentTaskDto;
    }

}
