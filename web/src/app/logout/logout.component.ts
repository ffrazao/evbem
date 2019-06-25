import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(
    private _loginService: LoginService,
    private _router: Router,
    private _toastr: ToastrService
  ) {
  }

  ngOnInit() {
    this._loginService.logout().subscribe(
      (res) => {
        this._router.navigate(['/home']);
        this._toastr.success('Executado com sucesso!', 'Logout');
    });
  }

}