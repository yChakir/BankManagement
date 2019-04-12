import {Component, OnInit} from '@angular/core';
import {LayoutService} from "./core/layout.service";
import {AuthService} from "./core/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Bank Management';

  isCollapsed: boolean = false;

  isAuthenticated: boolean = false;

  constructor(
    private authService: AuthService,
    private layoutService: LayoutService
  ) {
  }

  ngOnInit(): void {
    this.layoutService.getIsCollapsed().subscribe(value => this.isCollapsed = value);
    this.authService.getIsAuthenticated().subscribe(value => this.isAuthenticated = value);
  }

}
