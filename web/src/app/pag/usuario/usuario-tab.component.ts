import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Router, ActivatedRoute, Route } from '@angular/router';

import { Usuario } from 'src/app/entidade/usuario';
import { CrudTabComponent } from 'src/app/comum/componente/crud-tab-component';
import { CrudConfig } from 'src/app/comum/componente/crud-config';

/**
 * @title Basic use of `<table mat-table>`
 */
@Component({
  selector: 'usuario-tab',
  styleUrls: ['./usuario-tab.component.scss'],
  templateUrl: './usuario-tab.component.html',
})
export class UsuarioTabComponent extends CrudTabComponent implements OnInit {

  constructor(
    protected _config: CrudConfig,
    protected _router: Router,
    protected _actr: ActivatedRoute
  ) {
    super(_config, ['/pag', 'usuario']);
  }

  ngOnInit(): void {
    this._actr.data.subscribe((data: any) => {
      // carregar a tabela
      this.config.fonteDados = new MatTableDataSource<Usuario>(data.tabela);
    });
    // configurar o componente
    this.config.colunasExibidas = ['select', 'indice', 'nome', 'login', 'email', 'tipo'];
    this.config.selecaoRegistros = new SelectionModel<Usuario>(this.config.permitirMultiSelecao, this.config.selecaoInicial);
    this.config.fonteDados.paginator = this.paginator;
  }

  public getRoute(): Route {
    return this._router.config.find(v => v.path == 'pag')['_loadedConfig'].routes.find(v => v.path == 'usuario/:id');
  }

  public ver(pos: number = 0) {
    this.config.pos = this.config.selecaoRegistros.isEmpty() ? pos : 0;
    let url = this._urlPrincipal.slice(0);
    url.push(this.config.ids[this.config.pos].id);
    this.getRoute().data.config = this.config;
    this._router.navigate(url);
  }

}