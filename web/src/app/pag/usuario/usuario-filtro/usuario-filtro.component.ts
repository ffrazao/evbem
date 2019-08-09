import { Component, OnInit, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, Route } from '@angular/router';
import { CrudConfig } from 'src/app/comum/componente/crud-config';

@Component({
  selector: 'app-usuario-filtro',
  templateUrl: './usuario-filtro.component.html',
  styleUrls: ['./usuario-filtro.component.scss']
})
export class UsuarioFiltroComponent implements OnInit {

  @Input()
  @Output()
  private config: CrudConfig;

  constructor(
    private _formBuilder: FormBuilder,
    private _router: Router
    ) { }

  ngOnInit() {
    if (this.config && !this.config.filtro) {
      this.config.filtro = this.criarFormulario();
    }
  }

  criarFormulario() {
    return this._formBuilder.group({
      nome: [null, []],
      login: [null, []],
      perfil: [null, []],
    });
  }

  filtrar() {
    this.config.fonteDados = null;
    this.getRoute().data.config = this.config;
    this._router.navigate(['/pag', 'usuario'], {skipLocationChange: true});
  }

  public getRoute(): Route {
    return this._router.config.find(v => v.path == 'pag')
    ['_loadedConfig'].routes.find(v => v.path == 'usuario');
  }

}
