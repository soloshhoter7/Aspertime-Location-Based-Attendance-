import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../service/employee.service';
import { Employee } from '../model/Register.model';
import { MatDialogRef } from '@angular/material';
import {MatDialog,MatDialogConfig} from '@angular/material';
import { HttpService } from '../service/http.service';
import { EmployeeProfileModel } from '../model/EmployeeProfile.model';
import { HttpClient } from '@angular/common/http';
import { AngularFireStorage } from '@angular/fire/storage';
import {finalize} from "rxjs/operators";
import { HomeComponent } from '../home/home.component';
import {UpdateInfoComponent} from "../profile/update-info/update-info.component";
import { attendance } from '../model/attendance.model';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';
import { AttendanceLog } from '../model/attendancelog.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  employee: Employee;
  GenderFemale = false;
  disableUpload = false;
  userImage: string;
  imageUrlMale: string = "../assets/icons/default-user-image-male.png";
  imageUrlFemale: string = "../assets/icons/default-user-image-female.png";
  fileToUpload: File;
  ImageUrl;
  AttendanceLog:AttendanceLog[]=[];
  employeeProfile: EmployeeProfileModel = new EmployeeProfileModel();
  constructor(public empService: EmployeeService,
    public httpMethods: HttpService,
    public http:HttpClient,
    public storage:AngularFireStorage,
    public home:HomeComponent,
    public dialog:MatDialog,
    public snackBar: MatSnackBar) { }
    config: MatSnackBarConfig = {
      duration: 2000,
      horizontalPosition: 'center',
      verticalPosition: 'top'
    }
  
  ngOnInit() {
    this.employee = this.empService.empd;
    if(this.empService.ImageUrl!=null){
      this.userImage=this.empService.ImageUrl;
    }else{
      if (this.GenderFemale == false && this.employee.Gender == 'Female') {
        this.GenderFemale = true;
        this.userImage = this.imageUrlFemale;
      }
      else {
        this.GenderFemale = false;
        this.userImage = this.imageUrlMale;
      }
    }
    this.getWeeklyAttendance();
   
  }

  onFileSelected(event: any) {
    const target: HTMLInputElement = <HTMLInputElement>event.target;
    const files: FileList = target.files;
    this.fileToUpload = files[0];
    console.log("file to upload =>" + this.fileToUpload.name);

    //this is all for reading the file
    var reader = new FileReader();
    reader.onload = (event: any) => {
      this.userImage = event.target.result;
      console.log(event);
      this.disableUpload = true;
    }
    reader.readAsDataURL(this.fileToUpload);
  }
  onUpload() {
  var filepath =`${"employeeImages"}/${this.fileToUpload.name}_${this.empService.empd.userid}`; 
  const fileRef = this.storage.ref(filepath);
      this.storage.upload(filepath, this.fileToUpload).snapshotChanges().pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe((url) => {
          this.employeeProfile.employeeId=this.empService.empd.userid;
          this.employeeProfile.fileDownloadPath=url;     
          this.sendImageToServer();
          this.empService.ImageUrl=this.employeeProfile.fileDownloadPath;
          this.home.setProfile();   
          this.snackBar.open("Image Uploaded successfully","close",this.config);
          })
        })
      ).subscribe();

    }
    onUpdate(){
      const dialogConfig = new MatDialogConfig();
      dialogConfig.autoFocus=false;
      this.dialog.open(UpdateInfoComponent,dialogConfig);
    }
  //for uploading image to server  
  sendImageToServer(){
   this.httpMethods.SendImagePath(this.employeeProfile.fileDownloadPath,this.employeeProfile.employeeId).subscribe();
  }
  getWeeklyAttendance(){
    this.httpMethods.GetWeeklyAttendance(this.empService.empd.userid).subscribe(data=>{
      console.log('-------------------------------------------------');
      this.AttendanceLog=data;
      console.log(this.AttendanceLog);
    })
  }

}


