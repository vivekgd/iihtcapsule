import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TaskManagerService} from '../service/task-manager.service';
import {Task} from '../model/task.model';
import {ParentTask} from '../model/parent-task.model';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  public taskModel: Task = new Task();
  public parentTasks: ParentTask[];

  constructor(private router: Router, private taskManagerService: TaskManagerService) {
  }

  ngOnInit() {
    this.getAllParentTasks();
  }

  onSubmit(formData) {
    this.taskModel = formData;

    this.taskManagerService.addTask(this.taskModel)
      .subscribe(
        response => {
          /*Reset Task Model*/
          this.router.navigate(['/viewtask']);
          this.taskModel = new Task();
        }
      );
  }

  getAllParentTasks() {
    this.taskManagerService.getAllParentTasks()
      .subscribe(data => this.parentTasks = data);
  }
}
