import {Component, OnInit} from '@angular/core';
import {LayoutService} from "../../../core/layout.service";
import {UserService} from "../../../core/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isCollapsed: boolean = false;

  isAuthenticated: boolean = false;

  constructor(
    private authService: UserService,
    private layoutService: LayoutService
  ) {
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
