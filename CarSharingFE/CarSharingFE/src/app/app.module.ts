import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { FaresComponent } from './components/fares/fares.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { CarmapComponent } from './carmap/carmap.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { NavbarAdminComponent } from './components/admin/navbar-admin/navbar-admin.component';
import { HomeUserComponent } from './components/user/home-user/home-user.component';
import { NavbarUserComponent } from './components/user/navbar-user/navbar-user.component';
import { ManageUsersComponent } from './components/admin/manage-users/manage-users.component';
import { ManageVehiclesComponent } from './components/admin/manage-vehicles/manage-vehicles.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ManageRentalsComponent } from './components/admin/manage-rentals/manage-rentals.component';
import { AddUserFormComponent } from './components/admin/add-user-form/add-user-form.component';
import { AddVehicleFormComponent } from './components/admin/add-vehicle-form/add-vehicle-form.component';
import { UpdateUserFormComponent } from './components/admin/update-user-form/update-user-form.component';
import { UpdateVehicleFormComponent } from './components/admin/update-vehicle-form/update-vehicle-form.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FaresComponent,
    NavbarComponent,
    LogInComponent,
    SignUpComponent,
    CarmapComponent,
    HomeAdminComponent,
    NavbarAdminComponent,
    HomeUserComponent,
    NavbarUserComponent,
    ManageUsersComponent,
    ManageVehiclesComponent,
    ManageRentalsComponent,
    AddUserFormComponent,
    AddVehicleFormComponent,
    UpdateUserFormComponent,
    UpdateVehicleFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    NgIf,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
