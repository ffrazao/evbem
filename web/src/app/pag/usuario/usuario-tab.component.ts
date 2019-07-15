import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
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
      this.fonteDados = new MatTableDataSource<Usuario>(data.tabela);
  
      // configurar o componente
      this.fonteDados.paginator = this.paginator;
      this.permitirMultiSelecao = true;
      this.selecaoInicial = [];
      this.selecaoRegistros = new SelectionModel<Usuario>(this.permitirMultiSelecao, this.selecaoInicial);
      this.colunasExibidas = ['select', 'indice', 'nome', 'login', 'email', 'tipo'];
      this.quantidadeRegistros = 0;
      this.tamanhoPagina = 10;
    });
  }

  public getRoute(): Route {
    return this._router.config.find(v=>v.path == 'pag')['_loadedConfig'].routes.find(v=>v.path == 'usuario/:id');
  }
  
  onPaginateChange(event){
    this.paginaAtual = event.pageIndex;
    this.tamanhoPagina = event.pageSize;
    console.log('onPaginateChange', event);
  }
  
  public ver(pos: number = 0) {
    console.log('ver posicao ', pos);
    // exemplo de passagem de parametros via propriedade data
    let lista = this.getIdList(pos);
    
    if (lista.idList.length) {
      let url : string[] = this._urlPrincipal.slice(0);
      url.push(lista.idList[lista.pos].toString());
      this.getRoute().data.config = {
        paginaAtual: this.paginaAtual, 
        tamanhoPagina: this.tamanhoPagina, 
        filtro: {}, 
        lista
      };
      this._router.navigate(url);
    }
    
  }

}