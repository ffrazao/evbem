import { Pessoa } from '../principal/pessoa';
import { PessoaTipo } from '../../dominio/pessoa/pessoa-tipo';

export class PessoaJuridica extends Pessoa {

    constructor(
        public id?: number,
        public nome?: string,
        public cnpj?: string,
    ) {
        super(id, nome, PessoaTipo.PJ);
    }
}
