import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AccountListComponent} from "./account-list/account-list.component";

const routes: Routes = [
  {path: "", component: AccountListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountsRoutingModule {
}
