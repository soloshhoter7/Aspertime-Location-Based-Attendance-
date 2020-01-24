import { Component, OnInit, Output, Input } from '@angular/core';
import { EventEmitter } from 'events';
import { TodayAttendanceModel } from 'src/app/model/TodayAttendance.model';

@Component({
  selector: 'app-today-attendance',
  templateUrl: './today-attendance.component.html',
  styleUrls: ['./today-attendance.component.css']
})
export class TodayAttendanceComponent implements OnInit {
  @Input() attendance:TodayAttendanceModel;
  email:string;
  constructor() { }
 toggleStatus=false;
  ngOnInit() {
    
  }
  toggleCard(){
    console.log("toggle");
    if(this.toggleStatus==false){
      this.toggleStatus=true;
    }
    else{
      this.toggleStatus=false;
    }
     
  }

}
