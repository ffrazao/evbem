import { Component, AfterViewInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

import { CrudComponent } from 'src/app/comum/componente/crud-component';
import { Usuario } from 'src/app/pag/usuario/usuario-list.component';



@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent extends CrudComponent implements AfterViewInit {

  // seleçao
  public registro: Usuario;

  constructor(
    protected _httpClient: HttpClient,
    protected _router: Router,
    protected _activatedRoute: ActivatedRoute
  ) {
    super(_httpClient, _router, _activatedRoute, ['/pag', 'usuario']);
  }

  ngAfterViewInit() {
    console.log(1);
    if (this.id) {
      this._activatedRoute.url.subscribe(url => {
        this._httpClient.get<Usuario>('http://localhost:8080/usuario/' + this.id).subscribe(
          registro => {
            this.registro = registro;
          }
        );
      });
    }
  }

}