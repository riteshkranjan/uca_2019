import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class StudentService {
  url = "http://18.224.60.213:3001/"
  constructor(private http: HttpClient) { }
  getStudents(){
    return this.http.get<any>(this.url+"student");
  }
}
