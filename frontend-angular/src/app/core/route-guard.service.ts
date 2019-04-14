import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "./user.service";
import {environment} from "../../environments/environment";
import {SecurityService} from "./security.service";

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  constructor(
    private userService: UserService,
    private router: Router,
    private securityService: SecurityService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    switch (state.url) {
      case environment.routes.login:
        return this.canActivateLogin();
      case environment.routes.register:
        return this.canActivateRegister();
      case environment.routes.forgotPassword:
        return this.canActivateForgotPassword();
      case  environment.routes.validateEmail:
        return this.canActivateValidateEmail();
      case environment.routes.resetPassword:
        return this.canActivateResetPassword();
      case environment.routes.profile:
        return this.canActivateProfile();
      case environment.routes.accountType:
        return this.canActivateAccountTypes();
      case environment.routes.accounts:
        return this.canActivateAccounts();
      case environment.routes.roles:
        return this.canActivateRoles();
      case environment.routes.rights:
        return this.canActivateRights();
      case environment.routes.history:
        return this.canActivateHistory();
      default:
        return true;
    }
  }

  canActivateLogin(): boolean | UrlTree {
    let result: boolean | UrlTree = true;

    if (this.userService.getCurrentAuthenticationState()) {
      result = this.router.parseUrl(environment.routes.accounts);
    }

    return result;
  }

  canActivateRegister(): boolean | UrlTree {
    let result: boolean | UrlTree = true;

    if (this.userService.getCurrentAuthenticationState()) {
      result = this.router.parseUrl(environment.routes.accounts);
    }

    return result;
  }

  canActivateForgotPassword(): boolean | UrlTree {
    let result: boolean | UrlTree = true;

    if (this.userService.getCurrentAuthenticationState()) {
      result = this.router.parseUrl(environment.routes.accounts);
    }

    return result;
  }

  canActivateValidateEmail(): boolean | UrlTree {
    let result: boolean | UrlTree = true;

    if (this.userService.getCurrentAuthenticationState()) {
      result = this.router.parseUrl(environment.routes.accounts);
    }

    return result;
  }

  canActivateResetPassword(): boolean | UrlTree {
    let result: boolean | UrlTree = true;

    if (this.userService.getCurrentAuthenticationState()) {
      result = this.router.parseUrl(environment.routes.accounts);
    }

    return result;
  }

  canActivateProfile() {
    let result: boolean | UrlTree = true;

    if (!this.userService.getCurrentAuthenticationState() || (
      !this.securityService.hasShowProfile &&
      !this.securityService.hasAllRights
    )) {
      result = this.router.parseUrl(environment.routes.login);
    }

    return result;
  }

  canActivateAccountTypes() {
    let result: boolean | UrlTree = true;

    if (!this.userService.getCurrentAuthenticationState() || (
      !this.securityService.hasShowAccountTypes &&
      !this.securityService.hasAllRights
    )) {
      result = this.router.parseUrl(environment.routes.login);
    }

    return result;
  }

  canActivateAccounts() {
    let result: boolean | UrlTree = true;
    if (
      !this.userService.getCurrentAuthenticationState() && (
        !this.securityService.hasShowAccounts &&
        !this.securityService.hasShowAccountsOwn &&
        !this.securityService.hasShowAccountsWaitingApproval &&
        !this.securityService.hasAllRights
      )
    ) {
      result = this.router.parseUrl(environment.routes.login);
    }

    return result;
  }

  canActivateRoles() {
    let result: boolean | UrlTree = true;

    if (!this.userService.getCurrentAuthenticationState() || (
      !this.securityService.hasShowRoles &&
      !this.securityService.hasAllRights
    )) {
      result = this.router.parseUrl(environment.routes.login);
    }

    return result;
  }

  canActivateRights() {
    let result: boolean | UrlTree = true;

    if (!this.userService.getCurrentAuthenticationState() || (
      !this.securityService.hasShowRights &&
      !this.securityService.hasAllRights
    )) {
      result = this.router.parseUrl(environment.routes.login);
    }

    return result;
  }

  canActivateHistory() {
    let result: boolean | UrlTree = true;

    if (!this.userService.getCurrentAuthenticationState() || (
      !this.securityService.hasShowHistory &&
      !this.securityService.hasAllRights
    )) {
      result = this.router.parseUrl(environment.routes.login);
    }

    return result;
  }
}
