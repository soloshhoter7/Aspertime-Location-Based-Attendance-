import { Component, OnInit } from '@angular/core';
import {Employee} from '../model/Register.model';
import {EmployeeService} from '../service/employee.service';
import { HttpService } from '../service/http.service';
import { attendance } from '../model/attendance.model';
import { loginModel } from '../model/Login.model';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit {
  loginModel:loginModel = new loginModel();
  attendanceLog:attendance=new attendance();
  time = new Date();
  PunchInTimeDisplay:any;
  PunchOutTimeDisplay:any;
  PunchInStatus:any;
  TotalTimeStatus=false;
  PunchOutStatus:any;
  PunchInAvailable=true;
  PunchOutAvailable=true;
  PunchInTime:string;
  PunchOutTime:string;
  totalTime:any;
  CurrentLocationLat;
  CurrentLocationLong;
  locationDistance;
  fakelat=28.4153437;
  fakelong=77.0324505;
  employee:Employee=new Employee();
  constructor(public empService:EmployeeService,
    public HttpMethods:HttpService,
    public Cookie:CookieService) { }

  ngOnInit() {
    this.employee=this.empService.empd;
    this.CheckHashCode();
    this.checkLeaveDateBeforePunchIn();
    this.getAttendance();
    this.getLocation();

  }
  checkLeaveDateBeforePunchIn(){
    this.HttpMethods.checkLeaveDateBeforePunchIn(this.empService.empd.userid).subscribe(res=>{
      if(res==false){
        console.log("today is a leave!");
        this.PunchInAvailable=false;
        this.PunchOutAvailable=false;
      }else{
        console.log("today is not a leave!");
      }
    })
  }
   
   CheckHashCode(){
     this.HttpMethods.CheckHashCode(this.empService.empd).subscribe(res=>{
       this.loginModel=res;
       console.log("Hashcode=>"+this.loginModel.HashCode);
       if(this.loginModel.HashCode!=this.Cookie.get("HashCode")){
         this.PunchInAvailable=false;
         this.PunchOutAvailable=false;
       }
     })
   }

   onPunchIn(){
    this.submitPunchIn();
    console.log('punchin');
   }

   onPunchOut(){
    this.submitPunchOut();
    console.log('punchout')
   }
  // // -----------------------------Interaction with HttpService layer ----------------------------------------------------------------
   submitPunchIn(){
    this.HttpMethods.PunchIn(this.employee.userid).subscribe(res=>{
      this.PunchInStatus=res;
      if(this.PunchInStatus==true){
        console.log('punch in successful !');
        this.getAttendance();
        this.PunchInTimeDisplay=true;
        this.PunchInAvailable=false;
        this.PunchOutAvailable=true;
       
      }
      else{
        console.log('punch in not possible');
        this.PunchInAvailable=false;
      }
    });
   }

   submitPunchOut(){
     this.HttpMethods.PunchOut(this.employee.userid).subscribe(res=>{
      this.PunchOutStatus=res;
      if(this.PunchOutStatus==true){
        this.TotalTimeStatus=true;
        console.log('punch out successful !');
        this.getAttendance();
        this.PunchOutAvailable=false;
        this.PunchOutTimeDisplay=true;
        this.totalTime=this.attendanceLog.totaltime;
      }
      else{
        console.log('punch out not possible');
        this.PunchOutAvailable=false;
      }
     })
   }

   setAttendanceLog(){
     if(this.attendanceLog==null){
       this.PunchInTimeDisplay=false;
       this.PunchOutTimeDisplay=false;
       this.PunchOutAvailable=false;
     }
    else if(this.attendanceLog.punchIn!=null&&this.attendanceLog.punchOut==null){
      this.PunchInAvailable=false;
      this.PunchInTimeDisplay=true;
    }
    else if(this.attendanceLog.punchIn!=null&&this.attendanceLog.punchOut!=null){
     this.PunchInAvailable=false;
     this.PunchOutAvailable=false;
     this.PunchInTimeDisplay=true;
     this.PunchOutTimeDisplay=true;
    }
     if(this.attendanceLog!=null){
      this.PunchInTime=this.attendanceLog.punchIn;
      this.PunchOutTime=this.attendanceLog.punchOut;
      this.totalTime=this.attendanceLog.totaltime;
     }
   }
  getAttendance(){
    this.HttpMethods.GetAttendance(this.employee).subscribe(data=>{
    this.attendanceLog=data;
    console.log(this.attendanceLog);
    this.setAttendanceLog();
  })
  }
  getLocation(){
    if (window.navigator && window.navigator.geolocation) {
      window.navigator.geolocation.getCurrentPosition(
          position => {
                  this.CurrentLocationLat=position.coords.latitude;
                  this.CurrentLocationLong=position.coords.longitude;
                  console.log(this.CurrentLocationLat+","+this.CurrentLocationLong);
                  this.getDistance();
          },
          error => {
              switch (error.code) {
                  case 1:
                      console.log('Permission Denied');
                      break;
                  case 2:
                      console.log('Position Unavailable');
                      break;
                  case 3:
                      console.log('Timeout');
                      break;
              }
          }
      );
  };
}
distanceCalculator(lat1, lon1, lat2, lon2, unit) {
	if ((lat1 == lat2) && (lon1 == lon2)) {
		return 0;
	}
	else {
		var radlat1 = Math.PI * lat1/180;
		var radlat2 = Math.PI * lat2/180;
		var theta = lon1-lon2;
		var radtheta = Math.PI * theta/180;
		var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
		if (dist > 1) {
			dist = 1;
		}
		dist = Math.acos(dist);
		dist = dist * 180/Math.PI;
		dist = dist * 60 * 1.1515;
		if (unit=="K") { dist = dist * 1.609344 }
		if (unit=="N") { dist = dist * 0.8684 }
		return dist;
	}
}
getDistance(){
  this.locationDistance= this.distanceCalculator(this.CurrentLocationLat,this.CurrentLocationLong,29.211252,77.0172853,'K');
  console.log(this.locationDistance);
  if(this.locationDistance>=100){
    this.PunchInAvailable=false;
    this.PunchOutAvailable=false;
  }
}
// ----------------------------------------------------------------------------------------------------------------------------------
}
