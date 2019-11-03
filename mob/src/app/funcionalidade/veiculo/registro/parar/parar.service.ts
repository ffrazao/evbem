import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApiService } from '../../../../comum/servico/externo/api.service';
import { ViagemParar } from './viagem-parar';

const func = '/veiculo';

@Injectable({ providedIn: 'root' })
export class PararService extends ApiService {

    constructor(protected http: HttpClient) {
        super(http);
    }

    public salvar(r: ViagemParar) {
        return new Observable(observer => {
            observer.next(true);
        });
    }

}
