import { Confirmacao } from '../../dominio/confirmacao';
import { Evento } from '../evento/evento';
import { Recurso } from '../principal/principal';
import { Tipo } from '../produto/tipo';
import { Local } from '../comum/local';
import { Evidencia } from '../evento/evidencia';
import { VeiculoEventoTipo } from 'src/app/dominio/veiculo/veiculo-evento-tipo';

export class VeiculoEvento extends Evento {

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
        public evidenciaList?: Evidencia[],
        public tipo?: VeiculoEventoTipo
    ) {
        super(id, planejamento, recurso, eventoTipo, local, local_descricao, inicio, termino, descricao, pai, evidenciaList);
    }
}