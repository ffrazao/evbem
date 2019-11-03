import { RecursoTipo } from '../../dominio/principal/recurso-tipo';

export abstract class Recurso {

    constructor(
        public id?: number,
        public recursoTipo?: RecursoTipo,
        public observacao?: string,
    ) {
    }

}
