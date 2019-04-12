import {Component, Input, OnInit} from '@angular/core';
import {LayoutService} from "../../../core/layout.service";
import {UserService} from "../../../core/user.service";

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css']
})
export class DrawerComponent implements OnInit {

  isCollapsed: Boolean = false;

  isAuthenticated: boolean = false;

  constructor(
    private authService: UserService,
    private layoutService: LayoutService
  ) { }

  ngOnInit() {
    this.isAuthenticated = this.authService.getCurrentAuthenticationState();
    this.layoutService.getIsCollapsed().subscribe(value => this.isCollapsed = value);
    this.authService.getIsAuthenticated().subscribe(value => this.isAuthenticated = value);
  }

}
