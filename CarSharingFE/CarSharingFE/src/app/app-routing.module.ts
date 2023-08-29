import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { FaresComponent } from './components/fares/fares.component';
import { LogInComponent } from './components/log-in/log-in.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'tariffe', component: FaresComponent},
  {path: 'login', component: LogInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
