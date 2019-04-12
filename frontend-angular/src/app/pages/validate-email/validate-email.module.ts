import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ValidateEmailPageComponent } from './validate-email-page/validate-email-page.component';

@NgModule({
  declarations: [ValidateEmailPageComponent],
  imports: [
    CommonModule
  ],
  exports: [ValidateEmailPageComponent]
})
export class ValidateEmailModule { }
