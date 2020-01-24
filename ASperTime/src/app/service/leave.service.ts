import { Injectable } from '@angular/core';
import { LeaveModel } from '../model/Leave.model';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {
  AdminLeaves:LeaveModel[]=[];
  EmployeeLeaves:LeaveModel[]=[];
  constructor() { }
  indexOfApprovalLeave;
  index;
  AddLeave(leave:LeaveModel){
    this.AdminLeaves.push(leave);
  }
  DeleteLeave(leave:LeaveModel){
    this.indexOfApprovalLeave = this.AdminLeaves.indexOf(leave);
    this.AdminLeaves.splice(this.indexOfApprovalLeave,1);
  }
  AddEmployeeLeave(leave:LeaveModel){
    console.log("leave in service->");
    console.log(leave);
    this.EmployeeLeaves.push(leave);

  }
  DeleteEmployeeLeave(leave:LeaveModel){
    this.index=this.EmployeeLeaves.indexOf(leave);
    console.log(this.index);
    this.EmployeeLeaves.splice(this.index,1);
    // this.EmployeeLeaves.splice(this.EmployeeLeaves.indexOf(leave),1);
    for(let leave of this.EmployeeLeaves){
      console.log(leave);
    }
  }
}