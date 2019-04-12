import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterRoutingModule } from './register-routing.module';
import {RegisterFormComponent} from "./register-form/register-form.component";
import {RegisterPageComponent} from "./register-page/register-page.component";

@NgModule({
  imports: [
    CommonModule,
    RegisterRoutingModule
  ],
  declarations: [RegisterFormComponent, RegisterPageComponent]
})
export class RegisterModule { }
