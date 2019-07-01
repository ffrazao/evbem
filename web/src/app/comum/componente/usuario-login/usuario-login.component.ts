import { Component } from '@angular/core';
import { LoginService } from 'src/app/login/login.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuario-login',
  templateUrl: './usuario-login.component.html',
  styleUrls: ['./usuario-login.component.scss']
})
export class UsuarioLoginComponent {

  user$: Observable<string>;

  constructor(
    private _loginService: LoginService,
    private router: Router) {

    this.user$ = _loginService.getUsuario();
  }

  login() {
    this._loginService.exibeLogin(null);
  }

  logout() {
    this._loginService.logout();
    this.router.navigate(['']);
  }
}
