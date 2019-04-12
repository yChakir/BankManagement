import {Component, OnInit} from '@angular/core';
import {LayoutService} from "../../../core/layout.service";
import {AuthService} from "../../../core/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isCollapsed: boolean = false;

  isAuthenticated: boolean = false;

  constructor(
    private authService: AuthService,
    private layoutService: LayoutService
  ) {
  }

  ngOnInit() {
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
