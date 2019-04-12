import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  isAuthenticated: boolean = false;

  marginTop: string = '6%';

  constructor() { }

  ngOnInit() {
    this.checkLogin();
  }

  private checkLogin() {
    if (this.isAuthenticated) {

    }
  }
}
