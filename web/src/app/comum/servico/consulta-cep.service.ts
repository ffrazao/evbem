import { Injectable } from '@angular/core';
import { NgxViacepService } from '@brunoc/ngx-viacep';

@Injectable({
  providedIn: 'root'
})
export class ConsultaCepService {

  constructor(
    private viacep: NgxViacepService
  ) {
  }

  buscarPorCep(cep: string) {
    return this.viacep.buscarPorCep(cep);
  }

}
