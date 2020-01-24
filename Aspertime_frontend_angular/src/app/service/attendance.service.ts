import { Injectable } from '@angular/core';
import { TodayAttendanceModel } from 'src/app/model/TodayAttendance.model';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  TodayAttendance: TodayAttendanceModel[] = [];

  constructor() { }
  addTodayAttendance(TodayAttendances:TodayAttendanceModel){
   this.TodayAttendance.push(TodayAttendances);
  }
}
