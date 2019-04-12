import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ForgotPasswordPageComponent } from './forgot-password-page/forgot-password-page.component';
import { ForgotPasswordFormComponent } from './forgot-password-form/forgot-password-form.component';

@NgModule({
  declarations: [ForgotPasswordPageComponent, ForgotPasswordFormComponent],
  imports: [
    CommonModule
  ],
  exports: [ForgotPasswordPageComponent]
})
export class ForgotPasswordModule { }
