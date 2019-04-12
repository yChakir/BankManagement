import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './login-page/login-page.component';
import { LoginFormComponent } from './login-form/login-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  declarations: [LoginPageComponent, LoginFormComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule
  ],
  exports: [LoginPageComponent]
})
export class LoginModule { }
