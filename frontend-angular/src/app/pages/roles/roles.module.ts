import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RolesRoutingModule} from './roles-routing.module';
import {RolesPageComponent} from "./roles-page/roles-page.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  declarations: [RolesPageComponent],
  imports: [
    CommonModule,
    RolesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule
  ]
})
export class RolesModule {
}
