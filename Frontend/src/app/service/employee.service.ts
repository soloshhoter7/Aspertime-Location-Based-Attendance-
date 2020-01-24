import { Injectable } from '@angular/core';
import { Employee } from '../model/Register.model';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  // public stayLoggedIn=false;
  public ImageUrl;
  public empd: Employee = new Employee();
  loginStatus: boolean = false;
  constructor(private htttpService: HttpService) { }
  pushEmployee(employee: Employee) {
    this.empd = employee;
    this.loginStatus = true;
  }
  pushImagePath(path:string){
    this.ImageUrl=path;
    console.log("In service =>"+this.ImageUrl);
  }

}
