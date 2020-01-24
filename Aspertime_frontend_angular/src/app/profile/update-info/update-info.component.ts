import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/service/employee.service';
import { Employee } from 'src/app/model/Register.model';
import { MatDialogRef } from '@angular/material';
import {MatDialog,MatDialogConfig} from '@angular/material';
import { HttpService } from 'src/app/service/http.service';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';
@Component({
  selector: 'app-update-info',
  templateUrl: './update-info.component.html',
  styleUrls: ['./update-info.component.css']
})
export class UpdateInfoComponent implements OnInit {

  constructor( public empService:EmployeeService,
  private Dialogref:MatDialogRef<UpdateInfoComponent>,
  private httpMethods:HttpService,
  public snackBar: MatSnackBar) { }
  employee=new Employee();
  config: MatSnackBarConfig = {
    duration: 3000,
    horizontalPosition: 'center',
    verticalPosition: 'top'
  }
  ngOnInit() {
    this.employee.name=this.empService.empd.name;
    this.employee.Department=this.empService.empd.Department;
    this.employee.Gender=this.empService.empd.Gender;
    this.employee.userid=this.empService.empd.userid;
    this.employee.Designation=this.empService.empd.Designation;
  }
  UpdateInfo(){
    console.log(this.employee);
    this.empService.empd=this.employee;
    this.httpMethods.UpdateEmployee(this.employee).subscribe();
    this.Dialogref.close();
    this.snackBar.open('Details updated Successfully ','close',this.config);
  }

}
