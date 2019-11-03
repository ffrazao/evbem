import { Dto } from './dto';

export class FiltroDto extends Dto {

    constructor(
        public pagina?: number,
        public tamanho?: number,
        public chegouAoFim?: boolean,
        ) {
        super();
        this.pagina = !this.pagina ? 1 : this.pagina;
        this.tamanho = !this.tamanho ? 1 : this.tamanho;
    }

}
