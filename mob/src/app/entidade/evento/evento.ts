import { Confirmacao } from '../../dominio/confirmacao';
import { Local } from '../comum/local';
import { Recurso } from '../principal/principal';
import { EventoTipo } from './evento-tipo';
import { Evidencia } from './evidencia';
import { Sincrono } from '../sincrono';

export class Evento implements Sincrono {

    public idServidor?: number;
    public sincronizado?: Date;
    public apagar?: boolean;

    constructor(
        public id?: number,
        public planejamento?: Confirmacao,
        public recurso?: Recurso,
        public eventoTipo?: EventoTipo,
        public local?: Local,
        public localDescricao?: String,
        public inicio?: Date,
        public termino?: Date,
        public descricao?: string,
        public pai?: Evento,
        public evidenciaList?: Evidencia[],
    ) {
    }

}
