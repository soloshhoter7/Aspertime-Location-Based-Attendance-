import { Component, OnInit, Input } from '@angular/core';
import { LeaveModel } from 'src/app/model/Leave.model';
import { HttpService } from 'src/app/service/http.service';
import { Employee } from 'src/app/model/Register.model';
import { LeaveService } from 'src/app/service/leave.service';
@Component({
  selector: 'app-admin-leave',
  templateUrl: './admin-leave.component.html',
  styleUrls: ['./admin-leave.component.css']
})
export class AdminLeaveComponent implements OnInit {
  @Input() leave: LeaveModel;
  employee: Employee;
  ApprovalStatus = true;
  Approvaltext;
  constructor(
    public HttpMethods: HttpService,
    public leaveService:LeaveService
  ) { }

  ngOnInit() {
    this.HttpMethods.FindEmployee(this.leave.empId).subscribe(res => {
      this.employee = res;
      console.log("employee in admin-leave");
      console.log(this.employee);
    })

  }
  toggleStatus = false;

  toggleCard() {
    console.log("toggle");
    if (this.toggleStatus == false) {
      this.toggleStatus = true;
    }
    else {
      this.toggleStatus = false;
    }

  }
  ApproveLeave() {
    console.log(this.leave.LeaveId);
    this.HttpMethods.UpdateLeave(this.leave.LeaveId, "A", this.leave.LeaveType.toString(), this.leave.empId.toString()).subscribe(
      res => {
        this.Approvaltext = "Approved"
        this.ApprovalStatus = false;
        this.leaveService.DeleteLeave(this.leave);
      }
    );
  }

  DenyLeave() {
    this.HttpMethods.UpdateLeave(this.leave.LeaveId, "D",this.leave.LeaveType.toString(), this.leave.empId.toString()).subscribe(
      res => {
        this.Approvaltext = "Denied"
        this.ApprovalStatus = false;
        this.leaveService.DeleteLeave(this.leave);
      }
    );
  }

}
