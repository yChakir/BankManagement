import {Component, OnInit} from '@angular/core';
import {LayoutService} from "../../../core/layout.service";
import {UserService} from "../../../core/user.service";
import {Router} from "@angular/router";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isCollapsed: boolean = false;

  isAuthenticated: boolean = false;

  routes: Object = environment.routes;

  constructor(
    private authService: UserService,
    private layoutService: LayoutService,
    private router: Router
  ) {
  }

  get selected(): string {
    return this.router.url;
  }

  ngOnInit() {
    this.isAuthenticated = this.authService.getCurrentAuthenticationState();
    this.layoutService.getIsCollapsed().subscribe(value => this.isCollapsed = value);
    this.authService.getIsAuthenticated().subscribe(value => this.isAuthenticated = value);
  }

  toggle() {
    this.layoutService.toggle();
  }

  logout() {
    this.authService.logout();
  }
}
