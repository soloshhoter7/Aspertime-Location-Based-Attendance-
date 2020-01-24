import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/service/http.service';
import { EmployeeService } from 'src/app/service/employee.service';
import { TodayAttendanceModel } from 'src/app/model/TodayAttendance.model';
import { TodayAttendanceComponent } from './today-attendance/today-attendance.component';
import { AttendanceService } from 'src/app/service/Attendance.service';
import { LeaveService } from 'src/app/service/leave.service';
import { LeaveModel } from 'src/app/model/Leave.model';

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css']
})
export class ManageComponent implements OnInit {
  AdminLeaves:LeaveModel[] = [];
  TodayAttendance: TodayAttendanceModel[] = [];
  constructor(public httpMethods: HttpService,
    public empService: EmployeeService,
    public attendanceService: AttendanceService,
    public LeaveService:LeaveService) { }

  todayAttendance = new TodayAttendanceModel();
  ngOnInit() {
    this.TodayAttendance=this.attendanceService.TodayAttendance;
    this.getTodayAttendance();
    this.getAdminLeaves();
  }
  
  getTodayAttendance() {
    this.httpMethods.GetTodayAttendance(this.empService.empd.Department).subscribe(res => {
      console.log(res);
      this.TodayAttendance=res;
      for(let todayAttendance of this.TodayAttendance){
        this.attendanceService.addTodayAttendance(todayAttendance);
      }
    })
  }

  getAdminLeaves(){
    this.httpMethods.GetAdminLeaves(this.empService.empd.Department).subscribe(res=>{
     this.AdminLeaves=res;
     for(let leave of this.AdminLeaves){
       console.log(leave);
       this.LeaveService.AddLeave(leave);
     }
    }) 
  }
}
