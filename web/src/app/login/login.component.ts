import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Login } from '../entidade/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  login: Login;

  constructor(
    @Inject(MAT_DIALOG_DATA)
    private _data: Login,
    private _dialogRef: MatDialogRef<LoginComponent>,
  ) { }

  ngOnInit() {
    if (this._data) {
      this.login = this._data;
    }
    if (!this.login) {
      this.login = {usuario: '', senha: ''};
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