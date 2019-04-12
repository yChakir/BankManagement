import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../core/user.service";
import {NzMessageService} from "ng-zorro-antd";
import {Router} from "@angular/router";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  loading: boolean = false;

  isAuthenticated: boolean = false;

  routes: Object = environment.routes;

  form: FormGroup = this.builder.group({
    username: this.builder.control('yassine.chakir@ilemgroup.com', [Validators.required, Validators.email]),
    password: this.builder.control('password', [Validators.required, Validators.minLength(6)]),
  });

  constructor(
    private authService: UserService,
    private builder: FormBuilder,
    private messageService: NzMessageService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.isAuthenticated = this.authService.getCurrentAuthenticationState();
    this.authService.getIsAuthenticated().subscribe(value => this.isAuthenticated = value);
  }

  submit() {
    this.loading = true;
    this.authService
    .login(this.form.value)
    .then(() => {
        this.loading = false;
        this.router.navigateByUrl(environment.routes.accounts)
      },
      response => {
        this.loading = false;
        this.messageService.error(response.error.message);
      });
  }

}
