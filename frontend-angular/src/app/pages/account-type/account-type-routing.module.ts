import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AccountTypePageComponent} from "./account-type-page/account-type-page.component";

const routes: Routes = [
  {path: "", component: AccountTypePageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountTypeRoutingModule {
}
