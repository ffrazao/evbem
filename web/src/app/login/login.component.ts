import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Login } from '../entidade/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;
  public login = new Login();

  constructor(
    private _service: LoginService
  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'usuario': new FormControl(this.login.usuario, [
        Validators.required,
        Validators.minLength(4)
      ]),
      'senha': new FormControl(this.login.senha)
    });
  }

  efetuarLogin() {
    this._service.login(this.login.usuario, this.login.senha).
      subscribe(
        (res) => { console.log(res) }, 
        (err) => { console.log(err) }
      );
  }

}
