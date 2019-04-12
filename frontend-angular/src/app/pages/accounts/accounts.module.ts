import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountDetailsComponent } from './account-details/account-details.component';

@NgModule({
  declarations: [AccountListComponent, AccountDetailsComponent],
  imports: [
    CommonModule
  ],
  exports: [AccountListComponent]
})
export class AccountsModule { }
