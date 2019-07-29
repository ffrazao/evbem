import { Component, OnInit, Input, Output } from '@angular/core';

@Component({
  selector: 'usuario-tab',
  templateUrl: './tab.component.html',
  styleUrls: ['./tab.component.scss']
})
export class TabComponent implements OnInit {

  @Input() @Output() private dados;

  private paginaAtual: number = 1;

  @Input() @Output() private paginaNova: number = 1;

  @Input() @Output() private paginaTamanho: number = 10;

  constructor() { }

  ngOnInit() {
  }

  paginaPrimeiroRegistro(): number {
    return (parseInt('' + this.paginaAtual) - 1) * parseInt('' + this.paginaTamanho);
  }

  paginaUltimoRegistro(): number {
    return (parseInt('' + this.paginaPrimeiroRegistro()) + parseInt('' + this.paginaTamanho));
  }

  paginasTotal() {
    let v1 = parseInt('' + this.registrosTotal());
    let v2 = parseInt('' + this.paginaTamanho);
    return Math.floor(v1 / v2) + (v1 % v2 ? 1 : 0);
  }

  registrosTotal(): number {
    return this.dados ? this.dados.length : 0;
  }

  vaiPrimeiro() {
    this.vai(1);
  }

  vaiAnterior() {
    this.vai(this.paginaAtual - 1);
  }

  vaiProximo() {
    this.vai(this.paginaAtual + 1);
  }

  vaiUltimo() {
    this.vai(this.paginasTotal());
  }

  vai(pagina: number) {
    if (!pagina || pagina < 1 || isNaN(pagina)) {
      pagina = this.registrosTotal() ? 1 : 0;
    } else if (pagina > this.paginasTotal()) {
      pagina = this.paginasTotal();
    }
    this.paginaAtual = pagina;
    this.paginaNova = this.paginaAtual;
  }

}
