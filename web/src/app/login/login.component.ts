import { Component, OnInit, Inject } from '@angular/core';
 import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
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
  login = new Login('', '');

  constructor(
    @Inject(MAT_DIALOG_DATA)
    private _data: Login,
    private _dialogRef: MatDialogRef<LoginComponent>,
    private _router: Router
  ) { }

  ngOnInit() {
    if (this._data) {
       this.login = this._data;
    }
    this.loginForm = new FormGroup({
      'usuario': new FormControl(this.login.usuario, [Validators.required]),
      'senha': new FormControl(this.login.senha, [])
    });
  }

  cancelar() {
    this._dialogRef.close();
  }

  get usuario() { return this.loginForm.get('usuario'); }

  get senha() { return this.loginForm.get('senha'); }

}
