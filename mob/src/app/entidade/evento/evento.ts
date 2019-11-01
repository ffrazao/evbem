import { Confirmacao } from '../../dominio/confirmacao';
import { Local } from '../comum/local';
import { Recurso } from '../principal/principal';
import { Tipo } from './tipo';
import { Evidencia } from './evidencia';

export class Evento {
    
    constructor(
        public id?: number,
        public planejamento?: Confirmacao,
        public recurso?: Recurso,
        public eventoTipo?: Tipo,
        public local?: Local,
        public local_descricao?: String,
        public inicio?: Date,
        public termino?: Date,
        public descricao?: Date,
        public pai?: Evento,
        public evidenciaList?: Evidencia[]
    ) {
    }

}
