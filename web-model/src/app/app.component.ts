import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from './ferramenta/servico/seguranca/auth-service';
import { Usuario } from './ferramenta/entidade/sistema/usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {

  title = 'web-model';

  private usuario: Usuario = null;

  constructor(
    private service: AuthService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.service.getUsuario().subscribe(r => {
      this.usuario = r;
    });
  }

  logout() {
    this.service.logout().then(r => {
      if (r) {
        this.router.navigate(["/home"]);
      }
    });
  }

}
