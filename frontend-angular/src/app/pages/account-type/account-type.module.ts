import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountTypeRoutingModule } from './account-type-routing.module';
import { AccountTypePageComponent } from './account-type-page/account-type-page.component';

@NgModule({
  declarations: [AccountTypePageComponent],
  imports: [
    CommonModule,
    AccountTypeRoutingModule
  ]
})
export class AccountTypeModule { }
