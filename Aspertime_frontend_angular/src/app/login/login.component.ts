import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, MaxLengthValidator, FormGroup, FormControl } from '@angular/forms';
import { validateHorizontalPosition } from '@angular/cdk/overlay';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material';
import { loginModel } from '../model/Login.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from '../model/Register.model';
import { HttpService } from '../service/http.service';
import { EmployeeService } from '../service/employee.service';
import { EmployeeProfileModel } from '../model/EmployeeProfile.model';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  //---------------------------------------------default methods-------------------------------------------------------------------------- 
  ngOnInit() { 
    if(this.Cookie.get("stayLoggedIn")=="true"){
      this.user.username=this.Cookie.get("username");
      this.user.password=this.Cookie.get("password");
      this.LoginCheck();
    }
  }
  constructor(
     public router: Router,
     public DialogRef: MatDialogRef<LoginComponent>,
     public http: HttpClient,
     public HttpMethods: HttpService,
     public empService: EmployeeService,
     public Cookie:CookieService) { }
  //---------------------------------------------default methods/-------------------------------------------------------------------------
  expireDate = new Date();
  RegistrationName;
  //creating instances of model for dataBinding
  user = new loginModel();
  employee = new Employee();
  empd = new Employee();
  employeeProfile = new EmployeeProfileModel();

  // boolean implementations and ui formatting
  errorCode:string;
  CheckNameStatus=false;
  usernameStatus=false;
  stayLoggedIn;
  loginStatus: boolean = true;
  registerStatus = true;
  registerLoginStatus = false;
  SignInStatus = true;
  loginButton = 'Sign in'
  defaultGender = 'Male';
  defaultDepartment = 'CSE';
  retypedPassword: string;
  retypePasswordStatus = false;
  loginSuccessfulStatus: any;
  incorrectDetails: boolean = false;
  EmailValid: any = true;
  @ViewChild('f', { static: false }) LoginForm: NgForm;
  @ViewChild('r', { static: false }) RegisterForm: NgForm;


  // after submitting the login form
  onSubmit() {
    this.retypedPassword = this.LoginForm.value.retypePassword;
    if (this.validate(this.retypedPassword, this.LoginForm.value.password) || this.SignInStatus) {
      this.retypePasswordStatus = false;
      this.user.username = this.LoginForm.value.username;
      this.user.password = this.LoginForm.value.password;
      if (this.registerLoginStatus == true) {
        // this.CreateEmployeeLoginDetails();
        // this.onHome(); 
        this.CheckUsername();
      } else {
        console.log(this.user);
        this.LoginCheck();
      }

    }
  }
 randomString(length, chars) {
    var result = '';
    for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
    return result;
}
   rString = this.randomString(15, '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');

  //for saving login details for new user
  onLoginRegister() { 
    console.log(this.user);
    this.CreateEmployeeLoginDetails();

  }

  onHome() {
    this.DialogRef.close();
    this.router.navigate(['home']);
  }

  onRegister() {
    if (this.loginStatus = true) {
      this.loginStatus = false;
    }
    else {
      this.loginStatus == true;
    }
  }

  //after submitting employee personal details
  onSubmitRegister() {
    this.CheckName();
  }

  // validator function for retypePassword
  validate(retype: string, pass: string) {
    if (retype == pass) {
      return true;
    }
    else {
      this.retypePasswordStatus = true;
      return false;
    }
  }

 CheckName()
{
  this.CreateUppercaseName(this.employee.name);
  console.log(this.RegistrationName);
  this.HttpMethods.CheckName(this.RegistrationName,this.employee.Department,this.employee.Designation).subscribe(res=>{
    if(res==true){
      this.CreateEmployee();
    }
    else{
      this.CheckNameStatus=true;
      console.log("no employee record found !");
    }
  })
}  // ---------------------------------------------Interacting with http service ----------------------------------------------------------------------

  //for saving employee details in database
  CreateEmployee() {
   this.HttpMethods.CreateEmployee(this.employee)
      .subscribe(res => {
        console.log(res);
        this.EmailValid = res;
        if (this.EmailValid == true) {
          
          console.log("Employee created !");
            this.loginStatus = true;
            this.SignInStatus = false;
            this.registerStatus = false;
            this.registerLoginStatus = true;
            this.loginButton = 'Register';
          
        }
        else {
          console.log("email already existing");
        }
      }), err => {
        alert('an error occurred while sending request !');
      }
  }
  CheckUsername(){
    this.HttpMethods.checkUsername(this.user.username).subscribe(
      res=>{
        console.log("username status");
        if(res==true){
          this.CreateEmployeeLoginDetails();
      }
      else{
        this.usernameStatus=true;
        console.log("username already existing !");
      }
      });
  }
  //for saving employee login details in database 
  CreateEmployeeLoginDetails() {
    this.expireDate.setDate(this.expireDate.getDate()+365);
    this.user.HashCode=this.rString;
    this.Cookie.set("username",this.user.username,this.expireDate);
    this.Cookie.set("password",this.user.password,this.expireDate);
    this.Cookie.set("HashCode",this.user.HashCode,this.expireDate);
    this.HttpMethods.CreateEmployeeLoginDetails(this.user)
      .subscribe(res => {
        console.log("Employee login details created !");
        this.GetEmployee();
        this.onHome();
      }), err => {
        alert('an error occurred while sending request !');
      }
  }
  showDetails(employee: Employee) {
    console.log('in show Details->' + employee);
    this.onHome();
  }
  LoginCheck() {
    this.expireDate.setDate(this.expireDate.getDate()+365);
    this.HttpMethods.LoginCheck(this.user).subscribe(res => {
      this.loginSuccessfulStatus = res;
      console.log('status->' + this.loginSuccessfulStatus);
      if (this.loginSuccessfulStatus == true) {
        this.Cookie.set("username",this.user.username);
        this.Cookie.set("password",this.user.password);
        this.GetEmployee();
        if(this.stayLoggedIn!=null){
          this.Cookie.set("stayLoggedIn",this.stayLoggedIn.toString(),this.expireDate);
        }  
      } else {
        this.incorrectDetails = true;
      }
    });
  }

  GetEmployee() {
    this.HttpMethods.GetEmployee(this.user).subscribe(res => {
      this.empd = res;
      this.GetImage();
      console.log('in get employee' + this.empd.userid);
      this.empService.pushEmployee(this.empd);
    });
  }
  //for getting the user image 
  GetImage() {
    this.HttpMethods.getImagePath(this.empd.userid).subscribe(res => {
      if (res == null) {
        this.onHome();
      }
      this.employeeProfile = res;
      console.log(this.employeeProfile.fileDownloadPath);
      this.empService.pushImagePath(this.employeeProfile.fileDownloadPath);
      this.onHome();
    });
  }
//Converting Employee Name to UpperCase and without spaces
CreateUppercaseName(name:String)
{
 this.RegistrationName=name.toUpperCase();
 this.RegistrationName=this.RegistrationName.replace(/\s/g, "");
}  //---------------------------------Interacting with http service/-------------------------------------------------------------
} 
