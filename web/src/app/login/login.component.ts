import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Login } from '../entidade/login';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  login = new Login('a', 'a');

  constructor(
    private _service: LoginService,
    private _router: Router
  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'usuario': new FormControl(this.login.usuario, [Validators.required]),
      'senha': new FormControl(this.login.senha, [])
    });
  }

  efetuarLogin() {
    this._service.login(
      this.usuario.value,
      this.senha.value
    ).subscribe(
      (res) => {
        this._router.navigate(['/home']);
      }
    );
  }

  get usuario() { return this.loginForm.get('usuario'); }

  get senha() { return this.loginForm.get('senha'); }

}
