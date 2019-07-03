import { Component, AfterViewInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Usuario } from './usuario-list.component';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent implements AfterViewInit {

  // seleçao
  public sub;
  public ids: number[];
  public pos: number;

  constructor(
    private _httpClient: HttpClient,
    private _router: Router,
    private _activatedRoute:ActivatedRoute
    ) {
  }

  ngAfterViewInit() {

  this.sub = this._activatedRoute.paramMap.subscribe(params => { 
    let temp: string;
    temp = params.get('ids');
    if (!(/n/i).test(temp)) {
      try {
        this.ids = temp.split(',').map(e => Number.parseInt(e, 10));
        temp = params.get('pos');
        this.pos = Number.parseInt(temp, 10);
      } catch (e) {
      }
    }
    if (!this.ids) {
      this._router.navigate(['/pag', 'usuario']);
    }
    if (!this.pos) {
      this.pos = 0;
    }
    console.log(
      'ids', this.ids, 
      'pos', this.pos, 
      'id', this.id, 
      'proximo', this.idProximo, 
      'anterior', this.idAnterior,
      'primeiro', this.idPrimeiro,
      'ultimo', this.idUltimo);
  });

/*    this._httpClient.get<Usuario[]>('http://localhost:8080/usuario').subscribe(
      d => {
        console.log(d);
        this.dataSource = new MatTableDataSource<Usuario>(d);
        this.dataSource.paginator = this.paginator;
      }
    );*/
  }

  get id() {
    return this.ids[this.pos];
  }

  get idProximo() {
    return this.pos + 1 >= this.ids.length ? this.ids[this.pos]: this.ids[this.pos + 1];
  }

  get idAnterior() {
    return this.pos == 0 ? this.ids[0]: this.ids[this.pos - 1];
  }

  get idPrimeiro() {
    return this.ids[0];
  }

  get idUltimo() {
    return this.ids[this.ids.length - 1];
  }

}