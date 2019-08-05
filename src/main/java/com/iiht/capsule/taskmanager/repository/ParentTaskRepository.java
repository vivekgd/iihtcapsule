package com.iiht.capsule.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiht.capsule.taskmanager.entity.ParentTask;

public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {
    ParentTask findByParentId(Long id);
}
