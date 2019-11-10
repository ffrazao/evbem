import { Evento } from './evento';
import { Recurso } from '../principal/principal';
import { Funcao } from './funcao';
import { Confirmacao } from '../../dominio/confirmacao';
import { Sincrono } from '../sincrono';

export class Participacao implements Sincrono {
    
    public idServidor?: number;
    public sincronizado?: Date;
    public apagar?: boolean;

    constructor(
        public id?: number,
        public evento?: Evento,
        public recurso?: Recurso,
        public funcao?: Funcao,
        public principal?: Confirmacao,
    ) {
    }

}
