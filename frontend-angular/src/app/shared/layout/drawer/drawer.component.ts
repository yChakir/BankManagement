import {Component, OnInit} from '@angular/core';
import {LayoutService} from "../../../core/layout.service";
import {UserService} from "../../../core/user.service";
import {Router} from "@angular/router";
import {environment} from "../../../../environments/environment";
import {SecurityService} from "../../../core/security.service";

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css']
})
export class DrawerComponent implements OnInit {

  isCollapsed: Boolean = false;

  isAuthenticated: boolean = false;

  routes: Object = environment.routes;

  canShowProfile: boolean = this.securityService.hasShowProfile;

  canShowUser: boolean = this.securityService.hasShowProfile;

  canShowAccountType: boolean = this.securityService.hasShowProfile;

  canShowAccount: boolean = this.securityService.hasShowProfile;

  canShowRole: boolean = this.securityService.hasShowProfile;

  canShowRight: boolean = this.securityService.hasShowProfile;

  canShowHistory: boolean = this.securityService.hasShowProfile;

  hasAllRights: boolean = this.securityService.hasAllRights;

  constructor(
    private securityService: SecurityService,
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
    this.securityService.hasAllRights$.subscribe(value => this.hasAllRights = value);
    this.securityService.hasShowProfile$.subscribe(value => this.canShowProfile = value);
    this.securityService.hasShowUsers$.subscribe(value => this.canShowUser = value);
    this.securityService.hasShowAccountTypes$.subscribe(value => this.canShowAccountType = value);
    this.securityService.hasShowAccounts$.subscribe(value => this.canShowAccount = value);
    this.securityService.hasShowRoles$.subscribe(value => this.canShowRole = value);
    this.securityService.hasShowRights$.subscribe(value => this.canShowRight = value);
    this.securityService.hasShowHistory$.subscribe(value => this.canShowHistory = value);
  }
}
