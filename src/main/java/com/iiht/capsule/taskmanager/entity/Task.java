package com.iiht.capsule.taskmanager.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

	@Column(name = "task")
	private String task;

	@Column(name = "startDate")
	private Date startDate;

	@Column(name = "endDate")
	private Date endDate;

	@Column(name = "priority")
	private int priority;

	@Column(name = "in_progress")
	private boolean inProgress;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "parent_id")
	private ParentTask parentTask;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public ParentTask getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}

	@Override
	public String toString() {
		return "Task{" +
				"taskId=" + taskId +
				", task='" + task + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", priority=" + priority +
				", inProgress=" + inProgress +
				", parentTask=" + parentTask +
				'}';
	}
}
