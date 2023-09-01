import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { authGuard } from './auth/auth.guard';

import { HomeComponent } from './components/home/home.component';
import { FaresComponent } from './components/fares/fares.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { CarmapComponent } from './carmap/carmap.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { HomeUserComponent } from './components/user/home-user/home-user.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'mappa', component: CarmapComponent},
  {path: 'tariffe', canActivate: [authGuard], component: FaresComponent},
  {path: 'login', component: LogInComponent},
  {path: 'signup', component: SignUpComponent},
  {path: 'admin', component: HomeAdminComponent},
  {path: 'user', component: HomeUserComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
