import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LayoutService} from "./layout.service";
import {UserService} from "./user.service";

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [LayoutService, UserService]
})
export class CoreModule { }
