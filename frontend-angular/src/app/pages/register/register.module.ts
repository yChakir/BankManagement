import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterPageComponent } from './register-page/register-page.component';
import { RegisterformComponent } from './registerform/registerform.component';

@NgModule({
  declarations: [RegisterPageComponent, RegisterformComponent],
  imports: [
    CommonModule
  ],
  exports: [RegisterPageComponent]
})
export class RegisterModule { }
