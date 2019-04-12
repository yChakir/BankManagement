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
      default:
        return false;
    }
  }

  canActivateLogin(): boolean | UrlTree {
    let result: boolean | UrlTree = true;

    if (this.userService.getCurrentAuthenticationState()) {
      result = this.router.parseUrl(environment.routes.accounts);
    }

    return result;
  }
}
