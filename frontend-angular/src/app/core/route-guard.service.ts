import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "./user.service";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  constructor(
    private userService: UserService,
    private router: Router
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
}
