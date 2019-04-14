import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ForgotPasswordRoutingModule} from './forgot-password-routing.module';
import {ForgotPasswordPageComponent} from "./forgot-password-page/forgot-password-page.component";
import {ForgotPasswordFormComponent} from "./forgot-password-form/forgot-password-form.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  declarations: [ForgotPasswordPageComponent, ForgotPasswordFormComponent],
  imports: [
    CommonModule,
    ForgotPasswordRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule
  ]
})
export class ForgotPasswordModule {
}
