import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { WelcomePageComponent} from "./welcome-page/welcome-page.component";
import {FormsModule} from "@angular/forms";


const routes: Routes = [
  {path: '', component: WelcomePageComponent, pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true}), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
