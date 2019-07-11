import { Component, ViewChild } from "@angular/core";
import { CrudComponent } from './crud-component';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Route } from '@angular/router';

@Component({})
export abstract class CrudTabComponent extends CrudComponent {

  dataSource: MatTableDataSource<any>;
  displayedColumns: string[];
  quantidadeRegistros: number = 0;
  tamanhoPagina: number = 10;
  tamanhoPaginaOpcoes: [5, 10, 20];
  selection: SelectionModel<any>;
  _initialSelection : [];
  allowMultiSelect : boolean = true;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    protected _urlPrincipal: string[],
  ) {
    super(_urlPrincipal);
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected == numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  get totalSelecao(): number {
    return this._initialSelection && this._initialSelection.length ? this._initialSelection.length : null;
  }

  abstract getRoute(): Route;

  public getIdList(posGeral: number) {
    console.log(1);
    let pos = 0;
    let idList : number[] = [];
    if (this.selection && this.selection.selected && (this.selection.selected as []).length) {
      this.selection.selected.forEach(e => idList.push(e.id));
    } else if (this.dataSource && this.dataSource.data && (this.dataSource.data as []).length) {
      pos = posGeral;
      this.dataSource.data.forEach(e => idList.push(e.id));
    }
    return {pos, idList};
  }

}