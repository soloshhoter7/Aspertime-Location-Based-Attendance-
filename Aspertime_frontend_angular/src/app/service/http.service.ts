import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { loginModel } from '../model/Login.model';
import { Observable } from 'rxjs';
import { Employee } from '../model/Register.model';
import { attendance } from '../model/attendance.model';
import { EmployeeProfileModel } from '../model/EmployeeProfile.model';
import { AttendanceLog } from '../model/attendancelog.model';
import { LeaveModel } from '../model/Leave.model';
import { TodayAttendanceModel } from '../model/TodayAttendance.model';
import { checkDateModel } from '../model/checkDateModel.model';
@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }
  LoginCheck(user: loginModel): Observable<Object> {
    return this.http.get('http://localhost:5000/employee/login',
      {
        params:
        {
          username: user.username,
          password: user.password
        }
      });
  }

  //for saving employee login details
  CreateEmployeeLoginDetails(user: loginModel): Observable<Object> {
    return this.http.post('http://localhost:5000/employee/loginDetails', user);
  }

  //for saving employee personal details
  CreateEmployee(employee: Employee): Observable<Object> {
    return this.http.post('http://localhost:5000/employee/save', employee);
  }

  //for getting employee after login
  GetEmployee(user): Observable<Employee> {
    return this.http.get<Employee>('http://localhost:5000/employee/getEmployee', {
      params:
      {
        username: user.username,
        password: user.password
      }
    });
  }

  //for punching in
  PunchIn(employeeId: number): Observable<Object> {
    return this.http.get('http://localhost:5000/home/punchin',{
      params:
      {
        userid: employeeId.toString()
      }
    });
  }

  //for punching out
  PunchOut(employeeId: number): Observable<Object> {
    return this.http.get('http://localhost:5000/home/punchout',{
      params:
      {
        userid: employeeId.toString()
      }
    });
  }
  //for getting attendance record 
  GetAttendance(employee: Employee): Observable<attendance> {
    return this.http.post<attendance>('http://localhost:5000/home/getAttendance', employee);
  }
  //for sending the image download path
  SendImagePath(fileDownloadPath: string, empId: number): Observable<Object> {
    let formData = new FormData();
    formData.append('fileDownloadPath', fileDownloadPath);
    formData.append('empId', empId.toString());
    return this.http.post('http://localhost:5000/home/sendImage', formData);
  }
  //for recieving the image download path
  getImagePath(employeeId): Observable<EmployeeProfileModel> {
    return this.http.get<EmployeeProfileModel>('http://localhost:5000/home/getImage', {
      params:
      {
        empId: employeeId
      }
    })
  }

  //Update Employee Details
  UpdateEmployee(employee: Employee): Observable<Object> {
    console.log(employee);
    return this.http.post('http://localhost:5000/employee/update', employee);
  }
 //for getting attendance record 
 GetWeeklyAttendance(employeeId: number): Observable<AttendanceLog[]> {
  return this.http.get<AttendanceLog[]>('http://localhost:5000/home/getWeeklyAttendance', {
      params:
      {
        empId: employeeId.toString()
      }
    })
}

// for applying leaves
ApplyLeave(leave): Observable<Object> {
  return this.http.post('http://localhost:5000/home/leave',leave);
}
// for getting the submitted leaves
GetLeave(employeeId:number):Observable<LeaveModel[]>{
  return this.http.get<LeaveModel[]>('http://localhost:5000/home/getLeave',{
    params:{
      empId: employeeId.toString()
    }
  })
}
GetHello():Observable<Object>{
  return this.http.get('http://localhost:5000/home/sayHello');
}
// Getting Today's attendance
GetTodayAttendance(department:string):Observable<TodayAttendanceModel[]>{
  return this.http.get<TodayAttendanceModel[]>('http://localhost:5000/home/getTodayAttendance',{
    params:{
      department:department
    }
  })
}
//Checking username
checkUsername(username:string):Observable<Object>{
return this.http.get('http://localhost:5000/employee/checkUsername',{
  params:{
    username:username
  }
});
}

CheckHashCode(employee:Employee):Observable<loginModel>{
  return this.http.post<loginModel>('http://localhost:5000/employee/getLogin',employee);
}
//Checking Name in the Designation
CheckName(Name:string,department:string,designation:string):Observable<Object>{
return this.http.get('http://localhost:5000/employee/checkEmployeeName',{
  params:{
    name:Name,
    department:department,
    designation:designation
  }
})
} 
checkLeaveDate(checkDate:checkDateModel):Observable<Object>{
return this.http.post('http://localhost:5000/home/checkLeaveDate',checkDate);
}

GetAdminLeaves(department:string):Observable<LeaveModel[]>{
  return this.http.get<LeaveModel[]>('http://localhost:5000/home/getLeavesAdmin',{
    params:{
      department:department
    }
  })
}

UpdateLeave(LeaveId:number,ApprovalStatus:string,leaveType:string,empId:string):Observable<Object>{
  return this.http.get('http://localhost:5000/home/updateLeaveApproval',{
    params:{
      LeaveId:LeaveId.toString(),
      ApprovalStatus:ApprovalStatus,
      leaveType:leaveType,
      empId:empId
    }
  })
  
}
// Finding employee with employee Id
FindEmployee(empId:number):Observable<Employee>{
 return this.http.get<Employee>('http://localhost:5000/employee/findEmployee',{
   params:{
     empId:empId.toString()
   }
 })
}
//checking leave before punching in 
checkLeaveDateBeforePunchIn(empId:number):Observable<Object>{
  return this.http.get('http://localhost:5000/home/checkLeaveDateBeforePunchIn',{
    params:{
      empId:empId.toString()
    }
  })
}

cancelLeave(leaveId:number):Observable<Object>{
  console.log(leaveId);
  return this.http.get('http://localhost:5000/home/cancelLeave',{
    params:{
      leaveId:leaveId.toString()
    }
  })
}
}

