import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../core/user.service";
import {NzMessageService} from "ng-zorro-antd";
import {ActivatedRoute, Router} from "@angular/router";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-validate-email-form',
  templateUrl: './validate-email-form.component.html',
  styleUrls: ['./validate-email-form.component.css']
})
export class ValidateEmailFormComponent implements OnInit {

  loading: boolean = false;

  routes: Object = environment.routes;

  form: FormGroup = this.builder.group({
    username: this.builder.control(
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(100),
        Validators.email
      ]
    ),
    token: this.builder.control(
      '',
      [
        Validators.required,
        Validators.minLength(30),
        Validators.maxLength(50)
      ]
    ),
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
      if (this.form.valid) {
        this.submit();
      }
    })
  }

  submit() {
    this.loading = true;
    this.userService
    .validateEmail(this.form.value)
    .subscribe(
      () => {
        this.messageService.success("Email has been validated !");
        this.router.navigate([environment.routes.login], {queryParamsHandling: 'preserve'});
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
