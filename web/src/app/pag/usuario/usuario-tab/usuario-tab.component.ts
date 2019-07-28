import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Router, ActivatedRoute, Route } from '@angular/router';

import { Usuario } from 'src/app/entidade/usuario';
import { CrudTabComponent } from 'src/app/comum/componente/crud-tab-component';
import { CrudConfig } from 'src/app/comum/componente/crud-config';
import { FormBuilder } from '@angular/forms';

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
    protected _router: Router,
    protected _actr: ActivatedRoute,
    private _formBuilder: FormBuilder
  ) {
    super();
    console.log('criando tab...');
  }

  ngOnInit(): void {
    console.log('iniciando tab...');
    if (!this.config) {
      this.config = new CrudConfig(['/pag', 'usuario']);
      this.config.filtro = this._formBuilder.group({
        nome: ['Alice', []],
        login: [null, []],
        perfil: [null, []],
      });
    }
    this._actr.data.subscribe((data: any) => {
      // carregar as configurações
      if (data.config) {
        // console.log('Recuperar config tab');
        this.config = data.config; //Object.assign({}, data.config);
        delete data['config'];
      } else if (!this.config) {
        this.config = new CrudConfig(['/pag', 'usuario']);
      }
      // carregar a tabela
      this.config.fonteDados = new MatTableDataSource<Usuario>(data.tabela);
      this.config.fonteDados.paginator = this.paginator;
      // configurar o componente
      if (!this.config.colunasExibidas) {
        this.config.colunasExibidas = ['select', 'indice', 'nome', 'login', 'email', 'tipo'];
      }
      if (!this.config.selecaoRegistros) {
        this.config.selecaoRegistros = new SelectionModel<Usuario>(this.config.permitirMultiSelecao, this.config.selecaoInicial);
      }
    });
  }

  public getRoute(): Route {
    return this._router.config.find(v => v.path == 'pag')
    ['_loadedConfig'].routes.find(v => v.path == 'usuario')
    ['_loadedConfig'].routes.find(v => v.path == ':id');
  }

  public ver(pos: number = 0) {
    this.config.pos = this.config.selecaoRegistros && this.config.selecaoRegistros.isEmpty() ? pos : 0;
    let url = this.config.urlPrincipal.slice(0);
    url.push(this.config.id.toString());
    this.getRoute().data.config = this.config; //Object.assign({}, this.config);
    this._router.navigate(url);
  }

}