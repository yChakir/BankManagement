import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RegisterRoutingModule} from './register-routing.module';
import {RegisterFormComponent} from "./register-form/register-form.component";
import {RegisterPageComponent} from "./register-page/register-page.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule,
    RegisterRoutingModule,
  ],
  declarations: [RegisterFormComponent, RegisterPageComponent]
})
export class RegisterModule {
}
