import { RecursoTipo } from '../../dominio/principal/recurso-tipo';
import { Confirmacao } from '../../dominio/confirmacao';

export class Recurso {
    constructor(
        public id?: number,
        public recursoTipo?: RecursoTipo,
        public ativo?: Confirmacao,
        public observacao?: string,
    ) {

    }
}