import { FiltroDto } from 'src/app/comum/transporte/filtro';

export class PessoaFiltroDto extends FiltroDto {

    constructor(
        public pesq?: string[],
        public nome?: string[],
        public cpf?: string[],
    ) {
        super();
    }
}
