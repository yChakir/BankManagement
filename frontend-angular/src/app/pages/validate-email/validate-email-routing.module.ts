import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ValidateEmailPageComponent} from "./validate-email-page/validate-email-page.component";

const routes: Routes = [
  {path: "", component: ValidateEmailPageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ValidateEmailRoutingModule {
}
