import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ValidateEmailRoutingModule } from './validate-email-routing.module';
import {ValidateEmailPageComponent} from "./validate-email-page/validate-email-page.component";

@NgModule({
  declarations: [ValidateEmailPageComponent],
  imports: [
    CommonModule,
    ValidateEmailRoutingModule
  ]
})
export class ValidateEmailModule { }
