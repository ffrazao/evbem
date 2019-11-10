import { Component } from '@angular/core';

@Component({
  selector: 'app-viagem',
  templateUrl: 'viagem.page.html',
  styleUrls: ['viagem.page.scss']
})
export class ViagemPage {

  private estadoList = ['saida', 'rota', 'chegada'];

  private estado = 0;
  
  constructor() {}

  avancar() {
    this.setEstado(this.estado + 1);
  }

  setEstado(estado: number) {
    this.estado = estado;
  }

  retornar() {
    this.setEstado(this.estado - 1);
  }

}
