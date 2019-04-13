import {Component, OnInit} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../core/user.service";
import {NzMessageService} from "ng-zorro-antd";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-reset-password-form',
  templateUrl: './reset-password-form.component.html',
  styleUrls: ['./reset-password-form.component.css']
})
export class ResetPasswordFormComponent implements OnInit {

  loading: boolean = false;

  routes: Object = environment.routes;

  form: FormGroup = this.builder.group({
    username: this.builder.control('', [Validators.required, Validators.email]),
    token: this.builder.control('', [Validators.required, Validators.minLength(30)]),
    password: this.builder.control('', [Validators.required, Validators.minLength(8)]),
  });

  constructor(
    private userService: UserService,
    private messageService: NzMessageService,
    private router: Router,
    private builder: FormBuilder,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params.username) {
        this.form.controls.username.setValue(params.username);
      }
      if (params.token) {
        this.form.controls.token.setValue(params.token);
      }
    })
  }

  submit() {
    this.loading = true;
    this.userService
    .resetPassword(this.form.value)
    .subscribe(
      () => {
        this.messageService.success("Password reset successfully !");
        this.router.navigate([environment.routes.login], {preserveQueryParams: true});
        this.loading = false;
      },
      response => {
        console.log(response);
        this.messageService.error(response.error.message);
        this.loading = false;
      }
    );
  }
}
