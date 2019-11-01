import { PessoaJuridica } from '../pessoa/pessoa-juridica';

export class Empregador extends PessoaJuridica {
    
    constructor(
        public id?: number,
        public nome?: string,
        public cnpj?: string,
    ) {
        super(id, nome, cnpj);
    }

}
