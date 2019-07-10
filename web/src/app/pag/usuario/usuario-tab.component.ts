import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { Usuario } from 'src/app/entidade/usuario';
import { CrudTabComponent } from 'src/app/comum/componente/crud-tab-component';

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
    protected _actr: ActivatedRoute
  ) {
    super(['/pag', 'usuario']);
  }
  
  ngOnInit(): void {
    // carregar a tabela
    this.dataSource = new MatTableDataSource<Usuario>(this._actr.snapshot.data.tabela);

    // configurar o componente
    this.dataSource.paginator = this.paginator;
    this.selection = new SelectionModel<Usuario>(this.allowMultiSelect, this._initialSelection);
    this.displayedColumns = ['select', 'nome', 'login', 'email', 'tipo'];
    this._initialSelection = [];
    this.allowMultiSelect = true;
    this.quantidadeRegistros = 0;
  }

  public getRoute(): Route {
    return this._router
      .config.find(v=>v.path == 'pag')['_loadedConfig']
      .routes.find(v=>v.path == 'usuario/:id');
  }

}