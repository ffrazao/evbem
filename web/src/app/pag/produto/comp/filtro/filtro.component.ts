import { Component, OnInit, Input, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, Route } from '@angular/router';

import { CrudConfig } from 'src/app/comum/componente/crud-config';

@Component({
  selector: 'app-produto-filtro',
  templateUrl: './filtro.component.html',
  styleUrls: ['./filtro.component.scss']
})
export class FiltroComponent implements OnInit {

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
      produtoTipo: [null, []],
      marca: [null, []],
      modelo: [null, []],
      numeroSerie: [null, []],
    });
  }

  filtrar() {
    this.config.fonteDados = null;
    this.getRoute().data.config = this.config;
    this._router.navigate(['/pag', 'produto'], {skipLocationChange: true});
  }

  public getRoute(): Route {
    return this._router.config.find(v => v.path == 'pag')
    ['_loadedConfig'].routes.find(v => v.path == 'produto');
  }

}
