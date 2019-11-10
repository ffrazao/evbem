import { Component } from '@angular/core';
import { ViagemService } from './viagem/viagem.service';

@Component({
  templateUrl: 'registro.page.html',
  styleUrls: ['registro.page.scss']
})
export class RegistroPage {

  constructor(
    private viagemService: ViagemService
  ) { }

  public async viagem() {
    await this.viagemService.exibe();
  }

}
