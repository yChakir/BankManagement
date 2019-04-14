import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RightsRoutingModule} from './rights-routing.module';
import {RightsPageComponent} from "./rights-page/rights-page.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  declarations: [RightsPageComponent],
  imports: [
    CommonModule,
    RightsRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule
  ]
})
export class RightsModule {
}
