import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AccountTypeRoutingModule} from './account-type-routing.module';
import {AccountTypePageComponent} from './account-type-page/account-type-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgZorroAntdModule} from "ng-zorro-antd";

@NgModule({
  declarations: [AccountTypePageComponent],
  imports: [
    CommonModule,
    AccountTypeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule
  ]
})
export class AccountTypeModule {
}
