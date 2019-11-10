import { EventoTipo } from './evento-tipo'
import { Funcao } from './funcao'
import { Sincrono } from '../sincrono'

export class EventoTipoFuncao implements Sincrono {

    public idServidor?: number;
    public sincronizado?: Date;
    public apagar?: boolean;

    constructor(
        public id?: number,
        public eventoTipo?: EventoTipo,
        public funcao?: Funcao,
        public quantidade?: number,
    ) {
    }

}