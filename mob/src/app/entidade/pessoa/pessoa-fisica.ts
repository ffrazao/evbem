import { PessoaTipo } from '../../dominio/pessoa/pessoa-tipo';
import { Pessoa } from '../principal/pessoa';
import { Sexo } from '../../dominio/pessoa/pessoa-fisica-sexo';

export class PessoaFisica extends Pessoa {

    constructor(
        public id?: number,
        public nome?: string,
        public cpf?: string,
        public sexo?: Sexo,
        public cnhNumero?: string,
        public cnhCategoria?: string,
        public cnhVencimento?: Date,
    ) {
        super(id, nome, PessoaTipo.PF);
    }
    
}