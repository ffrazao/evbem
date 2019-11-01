import { Pessoa } from '../principal/pessoa';
import { PessoaTipo } from '../../dominio/pessoa/pessoa-tipo';

export class GrupoSocial extends Pessoa {

    constructor(
        public id?: number,
        public nome?: string,
    ) {
        super(id, nome, PessoaTipo.GS);
    }
    
}
