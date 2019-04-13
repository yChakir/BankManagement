import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../core/user.service";
import {NzMessageService} from "ng-zorro-antd";
import {Router} from "@angular/router";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  loading: boolean = false;

  routes: Object = environment.routes;

  form: FormGroup = this.builder.group({
    firstName: this.builder.control('', [Validators.required]),
    lastName: this.builder.control('', [Validators.required]),
    username: this.builder.control('', [Validators.required, Validators.email]),
    password: this.builder.control('', [Validators.required, Validators.minLength(8)]),
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
    .register(this.form.value)
    .subscribe(
      () => {
        this.messageService.success("Successful registration !");
        this.router.navigate([environment.routes.validateEmail], {queryParams: {username: this.form.controls.username.value}});
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
