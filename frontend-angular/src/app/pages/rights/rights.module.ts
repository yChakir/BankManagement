import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RightsRoutingModule } from './rights-routing.module';
import {RightsPageComponent} from "./rights-page/rights-page.component";

@NgModule({
  declarations: [RightsPageComponent],
  imports: [
    CommonModule,
    RightsRoutingModule
  ]
})
export class RightsModule { }
