import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: "login", loadChildren: "./pages/login/login.module#LoginModule"},
  {path: "register", loadChildren: "./pages/register/register.module#RegisterModule"},
  {path: "validate-email", loadChildren: "./pages/validate-email/validate-email.module#ValidateEmailModule"},
  {
    path: "forgot-password",
    loadChildren: "./pages/forgot-password/forgot-password.module#ForgotPasswordModule"
  },
  {path: "profile", loadChildren: "./pages/profile/profile.module#ProfileModule"},
  {path: "accounts", loadChildren: "./pages/accounts/accounts.module#AccountsModule"},
  {path: "roles", loadChildren: "./pages/roles/roles.module#RolesModule"},
  {path: "rights", loadChildren: "./pages/rights/rights.module#RightsModule"},
  {path: "history", loadChildren: "./pages/history/history.module#HistoryModule"}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
