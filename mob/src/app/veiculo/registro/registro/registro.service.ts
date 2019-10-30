import { Injectable } from '@angular/core';
import { ApiService } from 'src/app/comum/servico/externo/api.service';
import { HttpClient } from '@angular/common/http';

const func = '/veiculo';

@Injectable({ providedIn: 'root' })
export class RegistroService extends ApiService {

    constructor(protected http: HttpClient) {
        super(http);
    }

}
