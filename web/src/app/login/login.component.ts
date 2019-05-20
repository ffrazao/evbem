import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(
    private _service: LoginService
  ) { }

  ngOnInit() {
  }

  login() {
    this._service.login().
      then((res) => { console.log(res) }).
      catch((err) => { console.log(err) });
  }

}
