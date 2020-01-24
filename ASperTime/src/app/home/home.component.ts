import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../service/employee.service';
import { Employee } from '../model/Register.model';
import { HttpService } from '../service/http.service';
import { EmployeeProfileModel } from '../model/EmployeeProfile.model';
import { NotificationService } from '../service/notification.service';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';
import { CookieService } from 'ngx-cookie-service';
import { HostListener } from "@angular/core";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  ImageUrl;
  imageUrlMale: string = "../assets/icons/default-user-image-male.png";
  imageUrlFemale: string = "../assets/icons/default-user-image-female.png";
  employeeProfile: EmployeeProfileModel;
  CurrentLocationLat;
  CurrentLocationLong;
  employee: Employee = new Employee();
  GenderFemale = false;
  geolocationPosition: Object;
  toggle=true;
  toggleInMobile=true;
  isAdmin=false;
  innerWidth: any;
  mobHeight;
  mobWidth;
  config: MatSnackBarConfig = {
    duration: 3000,
    horizontalPosition: 'center',
    verticalPosition: 'top'
  }
  constructor(public empService: EmployeeService,
     public HttpMethods: HttpService, 
     public router: Router,
     public notificaton:NotificationService,
     public snackBar: MatSnackBar,
     public Cookie:CookieService) { }
  
  ngOnInit() {
    this.mobHeight = (window.screen.height)
    this.mobWidth = (window.screen.width) 
      console.log(this.mobHeight);
      console.log(this.mobWidth)
    if (this.empService.loginStatus == false) {
      this.router.navigate(['']);
    }
    this.employee = this.empService.empd;
    console.log(this.employee);
    this.setProfile();
   
  }
  
  IsAdmin(){
    if(this.empService.empd.Designation=="Professor"){
      this.isAdmin=true;
    }else{
      this.isAdmin=false;
    }
  }
  toggleButton(){
      if(this.mobWidth<=768){
        console.log(this.mobWidth);
      console.log("toggled");
      if(this.toggle==true){
        this.toggle=false;
      }
      else{
        this.toggle=true;
      }
      }
      
   }



 //on clicking logOut button
  setProfile(){
    if(this.empService.ImageUrl!=null){
      this.ImageUrl=this.empService.ImageUrl;
      console.log("in home / service =>"+this.empService.ImageUrl);
      console.log("in home"+this.ImageUrl);
    }else{
      if (this.GenderFemale == false && this.employee.Gender == 'Female') {
        this.GenderFemale = true;
        this.ImageUrl = this.imageUrlFemale;
      }
      else {
        this.GenderFemale = false;
        this.ImageUrl = this.imageUrlMale;
      }
    }
    this.IsAdmin();
  }
  onLogout() {
    this.empService.empd = null;
    this.empService.loginStatus = false;
    this.empService.ImageUrl = null;
    this.config['panelClass'] = ['notification', 'success'];
    this.snackBar.open('Logged out Successfully', 'close',this.config);
    this.Cookie.set("stayLoggedIn","false");
    this.router.navigate(['']);
  }
}



