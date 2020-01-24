import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, MaxLengthValidator, FormGroup, FormControl } from '@angular/forms';
import { LeaveModel } from '../model/Leave.model';
import { HttpService } from '../service/http.service';
import { EmployeeService } from '../service/employee.service';
import { Employee } from '../model/Register.model';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';
import { getMultipleValuesInSingleSelectionError } from '@angular/cdk/collections';
import { checkDateModel } from '../model/checkDateModel.model';
import { LeaveService } from '../service/leave.service';

@Component({
  selector: 'app-leave',
  templateUrl: './leave.component.html',
  styleUrls: ['./leave.component.css']
})
export class LeaveComponent implements OnInit {
  dateInString;
  empIdInString;
  checkDateStatus = false;
  checkLeaveDate= new checkDateModel();
  employee = new Employee();
  leaves:LeaveModel[]=[];
  LeaveScore;
  MonthlyScoreStatus:boolean=false;
  constructor(
    public httpMethods:HttpService,
    public empService:EmployeeService,
    public snackBar: MatSnackBar,
    public leaveService:LeaveService
    ) { }
    config: MatSnackBarConfig = {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'top'
    }
  leave:LeaveModel=new LeaveModel();
  @ViewChild('l', { static: false }) LeaveForm: NgForm;
  ngOnInit() {
    this.leaves.splice(0, this.leaves.length);
    console.log("length in leaves"+this.leaves.length)
    console.log("length in service"+this.leaveService.EmployeeLeaves.length);
    this.leaves=this.leaveService.EmployeeLeaves;
    this.employee=this.empService.empd;
    this.CalculateLeaveScore();
    this.GetLeave();
  
  }
//  getDate(){
//   this.dateInString = this.date.getDate()  + "-" + (this.date.getMonth()+1) + "-" + this.date.getFullYear();
//   console.log("date=>"+this.dateInString);
//  }

CalculateLeaveScore(){
  this.LeaveScore=this.empService.empd.MonthlyScore;
}

checkLeaveDetails(){
  this.leave.empId=this.empService.empd.userid;
  this.checkLeaveDate.date=this.leave.Date;
  this.checkLeaveDate.empId=this.leave.empId.toString();
  if(this.leave.LeaveType=="CL"&&this.empService.empd.MonthlyScore==0){
    this.MonthlyScoreStatus=true;
    console.log(this.MonthlyScoreStatus);
    console.log("can't apply casual leaves !");
  }else{
    this.httpMethods.checkLeaveDate(this.checkLeaveDate).subscribe(res=>{
      if(res==true){
        this.OnSubmitLeave();
      }else{
        this.checkDateStatus=true;
      }
    });
  } 
}
 OnSubmitLeave(){
   this.httpMethods.ApplyLeave(this.leave).subscribe(res=>{
     console.log(res);
     this.GetLeave();
     this.snackBar.open('Leave Submitted Successfully ','close',this.config);
   })
 }
 GetLeave(){
   this.httpMethods.GetLeave(this.empService.empd.userid).subscribe(res=>{
    this.leaveService.EmployeeLeaves.length=0;
    for(let leave of res){
       this.leaveService.AddEmployeeLeave(leave);
    }
    for(let leave of this.leaves){
      if(leave.ApprovalStatus=='P'){
        leave.ApprovalStatus="Pending";
      }
      else if(leave.ApprovalStatus=='A'){
        leave.ApprovalStatus="Approved";
      }
      else if(leave.ApprovalStatus=='D'){
        leave.ApprovalStatus="Denied";
      }
    }
   });
 }
}
