import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ResetPasswordPageComponent} from "./reset-password-page/reset-password-page.component";

const routes: Routes = [
  {path: "", component: ResetPasswordPageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResetPasswordRoutingModule { }
