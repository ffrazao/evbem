import { Component, AfterViewInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { ActivatedRoute, Router } from '@angular/router';

export interface Usuario {
  id: number;
  nome: string;
  login: string;
  email: string;
  tipo: string;
  ativo: string;
}

/**
 * @title Basic use of `<table mat-table>`
 */
@Component({
  selector: 'usuario-list',
  styleUrls: ['./usuario-list.component.scss'],
  templateUrl: './usuario-list.component.html',
})
export class UsuarioListComponent implements AfterViewInit {
  displayedColumns: string[] = ['select', 'nome', 'login', 'email', 'tipo'];
  dataSource: MatTableDataSource<Usuario>;
  quantidadeRegistros = 0;

  // seleçao
  selection;
  _initialSelection = [];
  allowMultiSelect = true;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(private _httpClient: HttpClient, 
    private _router: Router) {
    this.selection = new SelectionModel<Usuario>(this.allowMultiSelect, this._initialSelection);
  }

  ngAfterViewInit() {
    this._httpClient.get<Usuario[]>('http://localhost:8080/usuario').subscribe(
      d => {
        this.dataSource = new MatTableDataSource<Usuario>(d);
        this.dataSource.paginator = this.paginator;
      }
    );
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
    console.log(this.selection);
    return this._initialSelection && this._initialSelection.length ? this._initialSelection.length : null;
  }

  public selectedIds(): string[] {
    let result : any[] = ['/pag', 'usuario'];
    if (this.selection && this.selection.selected && (this.selection.selected as []).length) {
      result.push(this.selection.selected.map(e=>e.id).join());
      result.push('0');
    }

    // exemplo de passagem de parametros via propriedade data
    let rota = this._router
      .config.find(v=>v.path == 'pag')['_loadedConfig']
      .routes.find(v=>v.path == 'usuario/:pag/:ids');

    rota.data = {frz: 'teste'};

    return result;
  }

}