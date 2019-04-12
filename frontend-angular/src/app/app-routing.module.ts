import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginModule} from "./pages/login/login.module";
import {LoginPageComponent} from "./pages/login/login-page/login-page.component";
import {RegisterModule} from "./pages/register/register.module";
import {RegisterPageComponent} from "./pages/register/register-page/register-page.component";
import {AccountsModule} from "./pages/accounts/accounts.module";
import {AccountListComponent} from "./pages/accounts/account-list/account-list.component";
import {ForgotPasswordModule} from "./pages/forgot-password/forgot-password.module";
import {HistoryModule} from "./pages/history/history.module";
import {ProfileModule} from "./pages/profile/profile.module";
import {RightsModule} from "./pages/rights/rights.module";
import {RolesModule} from "./pages/roles/roles.module";
import {ValidateEmailModule} from "./pages/validate-email/validate-email.module";
import {ForgotPasswordPageComponent} from "./pages/forgot-password/forgot-password-page/forgot-password-page.component";
import {ValidateEmailPageComponent} from "./pages/validate-email/validate-email-page/validate-email-page.component";
import {ProfilePageComponent} from "./pages/profile/profile-page/profile-page.component";
import {RolesPageComponent} from "./pages/roles/roles-page/roles-page.component";
import {RightsPageComponent} from "./pages/rights/rights-page/rights-page.component";
import {HistoryPageComponent} from "./pages/history/history-page/history-page.component";

const routes: Routes = [
  {path: "login", component: LoginPageComponent},
  {path: "register", component: RegisterPageComponent},
  {path: "validate-mail", component: ValidateEmailPageComponent},
  {path: "forgot-password", component: ForgotPasswordPageComponent},
  {path: "profile", component: ProfilePageComponent},
  {path: "accounts", component: AccountListComponent},
  {path: "roles", component: RolesPageComponent},
  {path: "rights", component: RightsPageComponent},
  {path: "history", component: HistoryPageComponent}
];

@NgModule({
  imports: [
    AccountsModule,
    ForgotPasswordModule,
    HistoryModule,
    LoginModule,
    ProfileModule,
    RegisterModule,
    RightsModule,
    RolesModule,
    ValidateEmailModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
