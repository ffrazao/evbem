import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Login } from '../entidade/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  login = new Login('', '');

  constructor(
    private _service: LoginService
  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'usuario': new FormControl(this.login.usuario, [Validators.required]),
      'senha': new FormControl(this.login.senha, [])
    });
  }

  efetuarLogin() {
    console.log(this.usuario, this.senha);
    this._service.login(
      this.usuario.value,
      this.senha.value
    ).subscribe(
      (res) => {
        console.log('resposta login', res);
      },
      (err) => {
        console.log('erro login', err);
      }
    );
  }

  get usuario() { return this.loginForm.get('usuario'); }

  get senha() { return this.loginForm.get('senha'); }

}
