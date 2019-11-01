import { Evento } from './evento';
import { Recurso } from '../principal/principal';
import { Funcao } from './funcao';
import { Confirmacao } from '../../dominio/confirmacao';

export class Participacao {
    
    constructor(
        public id?: number,
        public evento?: Evento,
        public recurso?: Recurso,
        public funcao?: Funcao,
        public principal?: Confirmacao,
    ) {
    }

}
