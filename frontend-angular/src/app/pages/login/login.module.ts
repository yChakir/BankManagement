import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import {LoginPageComponent} from "./login-page/login-page.component";
import {LoginFormComponent} from "./login-form/login-form.component";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  declarations: [LoginPageComponent, LoginFormComponent],
  imports: [
    CommonModule,
    NgZorroAntdModule,
    LoginRoutingModule
  ]
})
export class LoginModule { }
