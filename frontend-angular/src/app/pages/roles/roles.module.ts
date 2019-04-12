import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RolesPageComponent } from './roles-page/roles-page.component';

@NgModule({
  declarations: [RolesPageComponent],
  imports: [
    CommonModule
  ],
  exports: [RolesPageComponent]
})
export class RolesModule { }
