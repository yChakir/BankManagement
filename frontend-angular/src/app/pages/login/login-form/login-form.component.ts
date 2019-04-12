import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../core/auth.service";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  isAuthenticated: boolean = false;

  form: FormGroup = this.builder.group({
    username: this.builder.control('yassine.chakir@ilemgroup.com', [Validators.required, Validators.email]),
    password: this.builder.control('password', [Validators.required, Validators.minLength(6)]),
  });

  constructor(
    private authService: AuthService,
    private builder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.authService
    .getIsAuthenticated()
    .subscribe(value => this.isAuthenticated = value);
  }

  submit() {
    if (!this.isAuthenticated) {
      this.authService.login();
    } else {
      this.authService.logout();
    }
  }

}
