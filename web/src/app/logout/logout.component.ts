import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(
    private _loginService: LoginService,
    private _toastr: ToastrService
  ) {
  }

  ngOnInit() {
    this._loginService.logout();
    this._toastr.success('Logout', 'Executado com sucesso!');
  }

}
