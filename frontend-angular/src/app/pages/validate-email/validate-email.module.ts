import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ValidateEmailRoutingModule} from './validate-email-routing.module';
import {ValidateEmailPageComponent} from "./validate-email-page/validate-email-page.component";
import {ValidateEmailFormComponent} from './validate-email-form/validate-email-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  declarations: [ValidateEmailPageComponent, ValidateEmailFormComponent],
  imports: [
    CommonModule,
    ValidateEmailRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule
  ]
})
export class ValidateEmailModule {
}
