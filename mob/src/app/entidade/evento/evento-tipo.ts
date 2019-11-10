import { RecursoTipo } from 'src/app/dominio/principal/recurso-tipo';
import { Confirmacao } from 'src/app/dominio/confirmacao';
import { Sincrono } from '../sincrono';

export class EventoTipo implements Sincrono {

    public idServidor?: number;
    public sincronizado?: Date;
    public apagar?: boolean;

    constructor(
        public id?: number,
        public codigo?: string,
        public nome?: string,
        public pai?: EventoTipo,
        public recursoTipo?: RecursoTipo[],
        public usoSistema?: Confirmacao,
    ) {
    }

}
