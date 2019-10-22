import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

import { LoginService } from './login.service';
import { Login } from './login';

@Component({
  templateUrl: 'login.page.html',
  styleUrls: ['login.page.scss']
})
export class LoginPage implements OnInit {

  private form: FormGroup;

  @ViewChild('focaliza', { static: false })
  private focaliza;

  private passwordType = 'password';

  private passwordIcon = 'eye-off';

  constructor(
    private service: LoginService,
    private formBuilder: FormBuilder,
  ) {
  }

  ionViewDidEnter() {
    setTimeout(() => {
      this.focaliza.setFocus();
    }, 500);
  }

  ngOnInit() {
    this.form = this.createForm(new Login());
  }

  private createForm(login: Login): FormGroup {
    return this.formBuilder.group({
      usuario: [login.usuario, Validators.required],
      senha: [login.senha, Validators.required],
    });
  }

  public hideShowPassword() {
    this.passwordType = this.passwordType === 'text' ? 'password' : 'text';
    this.passwordIcon = this.passwordIcon === 'eye-off' ? 'eye' : 'eye-off';
  }

  public onSubmit() {
    if (!this.form.valid) {
      return;
    }
    const login: Login = this.form.value as Login;
    console.log(login);
    this.service.login(login).subscribe((r) => {
      console.log('Result login =>', r);
    });
  }

}
