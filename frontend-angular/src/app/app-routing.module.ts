import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {environment} from "../environments/environment";

const routes: Routes = [
  {path: getRoute(environment.routes.login), loadChildren: "./pages/login/login.module#LoginModule"},
  {
    path: getRoute(environment.routes.register),
    loadChildren: "./pages/register/register.module#RegisterModule"
  },
  {
    path: getRoute(environment.routes.validateEmail),
    loadChildren: "./pages/validate-email/validate-email.module#ValidateEmailModule"
  },
  {
    path: getRoute(environment.routes.forgotPassword),
    loadChildren: "./pages/forgot-password/forgot-password.module#ForgotPasswordModule"
  },
  {path: getRoute(environment.routes.profile), loadChildren: "./pages/profile/profile.module#ProfileModule"},
  {
    path: getRoute(environment.routes.accounts),
    loadChildren: "./pages/accounts/accounts.module#AccountsModule"
  },
  {path: getRoute(environment.routes.roles), loadChildren: "./pages/roles/roles.module#RolesModule"},
  {path: getRoute(environment.routes.rights), loadChildren: "./pages/rights/rights.module#RightsModule"},
  {path: getRoute(environment.routes.history), loadChildren: "./pages/history/history.module#HistoryModule"}
];

function  getRoute(value: string): string {
  return value.substring(1);
}

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
