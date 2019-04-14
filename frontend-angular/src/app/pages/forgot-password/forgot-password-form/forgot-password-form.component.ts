import {Component, OnInit} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../core/user.service";
import {NzMessageService} from "ng-zorro-antd";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forgot-password-form',
  templateUrl: './forgot-password-form.component.html',
  styleUrls: ['./forgot-password-form.component.css']
})
export class ForgotPasswordFormComponent implements OnInit {

  loading: boolean = false;

  routes: Object = environment.routes;

  form: FormGroup = this.builder.group({
    username: this.builder.control('', [Validators.required, Validators.email])
  });

  constructor(
    private userService: UserService,
    private messageService: NzMessageService,
    private router: Router,
    private builder: FormBuilder
  ) {
  }

  ngOnInit() {
  }

  submit() {
    this.loading = true;
    this.userService
    .forgotPassword(this.form.value)
    .subscribe(
      () => {
        this.messageService.success("Mail sent successfully !");
        this.router.navigate([environment.routes.resetPassword], {queryParams: {username: this.form.controls.username.value}});
        this.loading = false;
      },
      response => {
        this.messageService.error(response.error.message);
        this.loading = false;
      }
    );
  }
}
