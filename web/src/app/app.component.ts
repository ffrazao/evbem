import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';

import { LoginService } from './login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  titulo = 'EvBem';
  envName = environment.envName;

  constructor(private _loginService: LoginService) {}

  login() {
    this._loginService.exibeLogin(null);
  }

}
