import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LayoutService} from "./layout.service";
import {AuthService} from "./auth.service";

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [LayoutService, AuthService]
})
export class CoreModule { }
