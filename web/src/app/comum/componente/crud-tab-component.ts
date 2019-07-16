import { Component, ViewChild } from "@angular/core";
import { CrudComponent } from './crud-component';
import { MatPaginator } from '@angular/material';
import { Route } from '@angular/router';
import { CrudConfig } from './crud-config';

@Component({})
export abstract class CrudTabComponent extends CrudComponent {

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    protected _config: CrudConfig,
    protected _urlPrincipal: string[],
  ) {
    super(_config, _urlPrincipal);
  }

  aplicarFiltro(filtro: string) {
    if (filtro) {
      this.config.fonteDados.filter = filtro.trim().toLowerCase();
    }
  }

  estaTudoSelecionado() {
    return this.config.selecaoRegistros.selected.length == this.config.fonteDados.data.length;
  }

  comutadorMestre() {
    this.estaTudoSelecionado() ?
      this.config.selecaoRegistros.clear() :
      this.config.fonteDados.data.forEach(row => this.config.selecaoRegistros.select(row));
  }

  get totalSelecao(): number {
    return this.config.selecaoInicial && this.config.selecaoInicial.length ? this.config.selecaoInicial.length : 0;
  }

  public onPaginateChange(event) {
    this.config.paginaAtual = event.pageIndex;
    this.config.tamanhoPagina = event.pageSize;
    console.log('onPaginateChange', event);
  }

  abstract getRoute(): Route;

  public getIdList(posGeral: number) {
    // console.log(1);
    let pos = 0;
    let idList: number[] = [];
    if (this.config.selecaoRegistros && this.config.selecaoRegistros.selected && (this.config.selecaoRegistros.selected as []).length) {
      this.config.selecaoRegistros.selected.forEach(e => idList.push(e.id));
    } else if (this.config.fonteDados && this.config.fonteDados.data && (this.config.fonteDados.data as []).length) {
      pos = posGeral;
      this.config.fonteDados.data.forEach(e => idList.push(e.id));
    }
    return { pos, idList };
  }

}