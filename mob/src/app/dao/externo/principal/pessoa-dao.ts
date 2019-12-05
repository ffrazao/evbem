import { Injectable } from '@angular/core';

import { BaseDaoExterno } from '../base/base-dao';
import { Pessoa } from '../../../entidade/principal/pessoa';
import { PessoaFiltroDto } from 'src/app/transporte/principal/pessoa.filtro.dto';

const funcionalidade = 'pessoa';

@Injectable({ providedIn: 'root' })
export class PessoaDaoExterno extends BaseDaoExterno<Pessoa, PessoaFiltroDto, Pessoa> {

    constructor(
    ) {
        super(funcionalidade);
    }

}
