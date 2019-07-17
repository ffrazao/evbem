import { Component, ViewChild } from "@angular/core";
import { MatPaginator } from '@angular/material';

import { CrudComponent } from './crud-component';

@Component({})
export abstract class CrudTabComponent extends CrudComponent {

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor() {
    super();
  }

  public aplicarFiltro(filtro: string) {
    if (filtro) {
      this.config.fonteDados.filter = filtro.trim().toLowerCase();
    }
  }

  public estaTudoSelecionado() {
    return this.config.selecaoRegistros.selected.length == this.config.fonteDados.data.length;
  }

  public comutadorMestre() {
    this.estaTudoSelecionado() ?
      this.config.selecaoRegistros.clear() :
      this.config.fonteDados.data.forEach(row => this.config.selecaoRegistros.select(row.id));
  }

  public get totalSelecao(): number {
    return this.config.selecaoInicial && this.config.selecaoInicial.length ? this.config.selecaoInicial.length : 0;
  }

  public onPaginateChange(event) {
    this.config.paginaAtual = event.pageIndex;
    this.config.tamanhoPagina = event.pageSize;
    // console.log('onPaginateChange', event);
  }

}