import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../core/user.service";
import {NzMessageService} from "ng-zorro-antd";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  loading: boolean = false;

  form: FormGroup = this.builder.group({
    firstName: this.builder.control('', [Validators.required]),
    lastName: this.builder.control('', [Validators.required]),
    username: this.builder.control('', [Validators.required, Validators.email]),
    password: this.builder.control('', [Validators.required, Validators.minLength(6)]),
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
        this.router.navigateByUrl('/validate-email');
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
