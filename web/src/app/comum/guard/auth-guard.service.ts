import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from 'src/app/login/login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private _loginService: LoginService,
    private _router: Router
  ) {
  }

  canActivate() {
    if (!this._loginService.estaLogado()) {
      this._loginService.exibeLogin(null);
      return false;
    }
    return true;
  }
}
