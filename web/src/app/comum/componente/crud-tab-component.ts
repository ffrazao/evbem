import { Component, ViewChild } from "@angular/core";
import { CrudComponent } from './crud-component';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Route } from '@angular/router';

@Component({})
export abstract class CrudTabComponent extends CrudComponent {

  protected fonteDados: MatTableDataSource<any>;
  protected colunasExibidas: string[];
  protected quantidadeRegistros: number = 0;
  protected tamanhoPagina: number = 10;
  protected tamanhoPaginaOpcoes = [5, 10, 20, 100];
  protected selecaoRegistros: SelectionModel<any>;
  protected selecaoInicial : [];
  protected permitirMultiSelecao : boolean = true;
  protected paginaAtual: number = 0;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    protected _urlPrincipal: string[],
  ) {
    super(_urlPrincipal);
  }

  applyFilter(filterValue: string) {
    this.fonteDados.filter = filterValue.trim().toLowerCase();
  }

  isAllSelected() {
    const numSelected = this.selecaoRegistros.selected.length;
    const numRows = this.fonteDados.data.length;
    return numSelected == numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selecaoRegistros.clear() :
      this.fonteDados.data.forEach(row => this.selecaoRegistros.select(row));
  }

  get totalSelecao(): number {
    return this.selecaoInicial && this.selecaoInicial.length ? this.selecaoInicial.length : null;
  }

  abstract getRoute(): Route;

  public getIdList(posGeral: number) {
    console.log(1);
    let pos = 0;
    let idList : number[] = [];
    if (this.selecaoRegistros && this.selecaoRegistros.selected && (this.selecaoRegistros.selected as []).length) {
      this.selecaoRegistros.selected.forEach(e => idList.push(e.id));
    } else if (this.fonteDados && this.fonteDados.data && (this.fonteDados.data as []).length) {
      pos = posGeral;
      this.fonteDados.data.forEach(e => idList.push(e.id));
    }
    return {pos, idList};
  }

}