import { Component, OnInit, ɵCompiler_compileModuleAndAllComponentsAsync__POST_R3__ } from '@angular/core';
import {MatDialog,MatDialogConfig} from '@angular/material';
import { LoginComponent } from '../login/login.component';
import { Router } from '@angular/router';
import { HttpService } from '../service/http.service';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  showSignIn = false;
  showRegister = false;
  constructor(
    public dialog:MatDialog,
    public httpMethods:HttpService,
    public Cookie:CookieService) { }
  
  onLogin(){ 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus=false;
    this.dialog.open(LoginComponent,dialogConfig);
    // this.httpMethods.GetHello().subscribe(res=>{
    //   console.log(res);
    // })
  }

  ngOnInit() {
    if(this.Cookie.get("stayLoggedIn")=="true"){
      this.onLogin();
    }
  }
  
}
