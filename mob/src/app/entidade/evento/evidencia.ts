import { Evento } from './evento';
import { EvidenciaTipo } from '../../dominio/evento/evidencia-tipo';
import { Sincrono } from '../sincrono';

export class Evidencia implements Sincrono {

    public idServidor?: number;
    public sincronizado?: Date;
    public apagar?: boolean;

    constructor(
        public id?: number,
        public evento?: Evento,
        public descricao?: string,
        public conteudo?: string,
        public evidenciaTipo?: EvidenciaTipo
    ) {
    }

}
