import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {environment} from "../environments/environment";
import {RouteGuardService} from "./core/route-guard.service";

const routes: Routes = [
  {
    path: "",
    redirectTo: getRoute(environment.routes.login),
    pathMatch: "full"
  },
  {
    path: getRoute(environment.routes.login),
    loadChildren: "./pages/login/login.module#LoginModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.register),
    loadChildren: "./pages/register/register.module#RegisterModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.validateEmail),
    loadChildren: "./pages/validate-email/validate-email.module#ValidateEmailModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.forgotPassword),
    loadChildren: "./pages/forgot-password/forgot-password.module#ForgotPasswordModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.resetPassword),
    loadChildren: "./pages/reset-password/reset-password.module#ResetPasswordModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.profile),
    loadChildren: "./pages/profile/profile.module#ProfileModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.accounts),
    loadChildren: "./pages/accounts/accounts.module#AccountsModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.accountType),
    loadChildren: "./pages/accounts/account-type.module#AccountTypeModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.roles),
    loadChildren: "./pages/roles/roles.module#RolesModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.rights),
    loadChildren: "./pages/rights/rights.module#RightsModule",
    canActivate: [RouteGuardService]
  },
  {
    path: getRoute(environment.routes.history),
    loadChildren: "./pages/history/history.module#HistoryModule",
    canActivate: [RouteGuardService]
  }
];

function getRoute(value: string): string {
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
