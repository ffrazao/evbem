import { Confirmacao } from '../../dominio/confirmacao';
import { Evento } from '../evento/evento';
import { Recurso } from '../principal/principal';
import { Local } from '../comum/local';
import { Evidencia } from '../evento/evidencia';
import { VeiculoEventoTipo } from '../../dominio/veiculo/veiculo-evento-tipo';
import { EventoTipo } from '../evento/evento-tipo';

export class VeiculoEvento extends Evento {

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
        public tipo?: VeiculoEventoTipo
    ) {
        super(id, planejamento, recurso, eventoTipo, local, localDescricao, inicio, termino, descricao, pai, evidenciaList);
    }

}
