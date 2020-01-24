import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from "@angular/material/dialog";
import { HeaderComponent } from './header/header.component';
import { Routes,RouterModule }  from '@angular/router';
import { HomeComponent } from './home/home.component';
import { APP_BASE_HREF } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AttendanceComponent } from './attendance/attendance.component';
import { LeaveComponent } from './leave/leave.component';
import { HttpService } from './service/http.service';
import { EmployeeService } from './service/employee.service';
import {AgmCoreModule} from '@agm/core';
import { ProfileComponent } from './profile/profile.component';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {AngularFireDatabaseModule} from 'angularfire2/database';
import { UpdateInfoComponent } from './profile/update-info/update-info.component';
import { MatSnackBarModule } from '@angular/material';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule } from  '@angular/material';
import { ManageComponent } from './home/manage/manage.component';
import { CookieService } from 'ngx-cookie-service';
import { TodayAttendanceComponent } from './home/manage/today-attendance/today-attendance.component';
import { AdminLeaveComponent } from './home/manage/admin-leave/admin-leave.component';
import { PendingLeaveComponent } from './leave/pending-leave/pending-leave.component';
const appRoutes: Routes = [
  {path:'',component:HeaderComponent},
  {path:'index',component:HeaderComponent},
  {path:'home',component:HomeComponent,
  children: [
    { path:'', component: AttendanceComponent },
    { path:'attendance',component:AttendanceComponent},
    { path:'leave',component:LeaveComponent},
    { path:'Manage',component:ManageComponent},
    {path:'MyProfile',component:ProfileComponent}
  ]
}
]
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    AttendanceComponent,
    LeaveComponent,
    ProfileComponent,
    UpdateInfoComponent,
    MainNavComponent,
    ManageComponent,
    TodayAttendanceComponent,
    AdminLeaveComponent,
    PendingLeaveComponent
  ],
  imports: [
    BrowserModule,
    AngularFireDatabaseModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    FormsModule,
    ReactiveFormsModule,
    NoopAnimationsModule,
    MatDialogModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    AngularFireStorageModule,
    MatSnackBarModule,
    AgmCoreModule.forRoot({
      apiKey:''
    }),
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule
  ],
  providers:[HttpService,EmployeeService,CookieService],
  bootstrap: [AppComponent],
  entryComponents:[LoginComponent,UpdateInfoComponent,ProfileComponent]
})
export class AppModule { }
