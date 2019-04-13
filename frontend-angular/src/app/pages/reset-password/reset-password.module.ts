import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResetPasswordRoutingModule } from './reset-password-routing.module';
import { ResetPasswordFormComponent } from './reset-password-form/reset-password-form.component';
import { ResetPasswordPageComponent } from './reset-password-page/reset-password-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  declarations: [ResetPasswordFormComponent, ResetPasswordPageComponent],
  imports: [
    CommonModule,
    ResetPasswordRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule
  ]
})
export class ResetPasswordModule { }
