import { PessoaTipo } from '../../dominio/pessoa/pessoa-tipo';
import { Recurso } from './principal';
import { RecursoTipo } from '../../dominio/principal/recurso-tipo';

export class Pessoa extends Recurso {
    
    constructor(
        public id?: number,
        public observacao?: string, 
        public nome?: string,
        public tipo?: PessoaTipo
    ) {
        super(id, RecursoTipo.PESSOA, observacao);
    }

}
