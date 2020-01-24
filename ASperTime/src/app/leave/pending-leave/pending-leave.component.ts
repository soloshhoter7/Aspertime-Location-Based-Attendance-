import { Component, OnInit, Input } from '@angular/core';
import { LeaveModel } from 'src/app/model/Leave.model';
import { HttpService } from 'src/app/service/http.service';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';
import { LeaveService } from 'src/app/service/leave.service';

@Component({
  selector: 'app-pending-leave',
  templateUrl: './pending-leave.component.html',
  styleUrls: ['./pending-leave.component.css']
})
export class PendingLeaveComponent implements OnInit {
  @Input() Leave: LeaveModel;
  toggleStatus = false;

  constructor(
    public httpMethods: HttpService,
    public SnackBar: MatSnackBar,
    public LeaveService:LeaveService
    ) { }

  config: MatSnackBarConfig = {
    duration: 3000,
    horizontalPosition: 'center',
    verticalPosition: 'top'
  }

  ngOnInit() {
    console.log("Leave->");
    console.log(this.Leave);
  }
  // for toggling button
  toggleCard() {
    console.log("toggle");
    console.log(this.Leave.LeaveId);
    if (this.toggleStatus == false) {
      this.toggleStatus = true;
    }
    else {
      this.toggleStatus = false;
    }
  }

  //for cancelling the leave
  CancelLeave() {
    console.log("cancel leave");
    console.log(this.Leave.ApprovalStatus);
    // this.LeaveService.DeleteEmployeeLeave(this.Leave);
    if(this.Leave.ApprovalStatus=="Approved"||this.Leave.ApprovalStatus=="Pending"){
      this.httpMethods.cancelLeave(this.Leave.LeaveId).subscribe(res => {
        this.LeaveService.DeleteEmployeeLeave(this.Leave);
        this.SnackBar.open("Leave cancelled successfully ","close",this.config);

       });
    }
  }
}
