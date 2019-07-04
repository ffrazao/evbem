import { Component, AfterViewInit, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from './usuario-list.component';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent implements OnInit, AfterViewInit {

  // seleçao
  public sub;
  public ids: number[];
  public pos: number;
  public novaPos: number;
  public registro: Usuario;

  constructor(
    private _httpClient: HttpClient,
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.sub = this._activatedRoute.paramMap.subscribe(params => {
      let temp: string;
      temp = params.get('ids');
      if (!(/n/i).test(temp)) {
        try {
          this.ids = temp.split(',').map(e => Number.parseInt(e, 10));
          temp = params.get('pos');
          this.setPos(Number.parseInt(temp, 10));
        } catch (e) {
        }
      }
      if (!this.ids) {
        this._router.navigate(['/pag', 'usuario']);
      }
      if (!this.pos) {
        this.setPos(0);
      }
      this.novaPos = this.pos + 1;
    });
  }

  ngAfterViewInit() {
    this._activatedRoute.url.subscribe(url => {
      this._httpClient.get<Usuario>('http://localhost:8080/usuario/' + this.id).subscribe(
        registro => {
          this.registro = registro;
        }
      );
    });
  }

  get id() {
    if (!this.ids || !Array.isArray(this.ids)) return null;
    if (!this.pos) { this.setPos(0); }
    if (this.pos >= this.ids.length) { this.setPos(this.ids.length - 1); }
    return this.ids[this.pos];
  }

  public vaiParaPrimeiro(): void {
    this.vaiPara(0);
  }

  public vaiParaAnterior(): void {
    this.vaiPara(this.pos - 1);
  }

  public vaiParaProximo(): void {
    this.vaiPara(this.pos + 1);
  }

  public vaiParaUltimo(): void {
    this.vaiPara(this.ids.length - 1);
  }

  public vaiPara(pos): void {
    pos = ("" + pos).trim();
    console.log(pos);
    if ((pos || pos == 0) && !isNaN(pos)) {
      this._router.navigate(['/pag', 'usuario', this.ids.join(), pos]);
    }
  }

  private setPos(vlr: number) {
    if (vlr < 0) {
      vlr = 0;
    } else if (vlr >= this.ids.length) {
      vlr = this.ids.length -1;
    }
    this.pos = vlr;
  }

}