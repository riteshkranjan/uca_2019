import { Component, OnInit} from '@angular/core';
import {StudentService} from './student.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  students = [];
  constructor(private studentService: StudentService){}
  ngOnInit() {
    this.studentService.getStudents()
      .subscribe(
        res => this.students = res,
        err => {
          console.log(err);
        }
      )
  }
}
