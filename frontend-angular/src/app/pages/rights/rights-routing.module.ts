import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RightsPageComponent} from "./rights-page/rights-page.component";

const routes: Routes = [
  {path: "", component: RightsPageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RightsRoutingModule {
}
