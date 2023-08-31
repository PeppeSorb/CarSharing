import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { FaresComponent } from './components/fares/fares.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { CarmapComponent } from './carmap/carmap.component';
import { authGuard } from './auth/auth.guard';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'mappa', component: CarmapComponent},
  {path: 'tariffe', canActivate: [authGuard], component: FaresComponent},
  {path: 'login', component: LogInComponent},
  {path: 'signup', component: SignUpComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
