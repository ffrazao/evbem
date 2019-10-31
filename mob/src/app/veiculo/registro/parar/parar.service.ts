import { Injectable } from '@angular/core';
import { ApiService } from 'src/app/comum/servico/externo/api.service';
import { HttpClient } from '@angular/common/http';
import { ViagemParar } from './viagem-parar';
import { Observable } from 'rxjs';

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
