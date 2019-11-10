import { Confirmacao } from '../../dominio/confirmacao';
import { Evento } from '../evento/evento';
import { Evidencia } from '../evento/evidencia';
import { Local } from '../comum/local';
import { Participacao } from '../evento/participacao';
import { Pessoa } from '../principal/pessoa';
import { Recurso } from '../principal/principal';
import { Sincrono } from '../sincrono';
import { UnidadeOrganizacional } from '../funcional/unidade-organizacional';
import { VeiculoEvento } from './veiculo-evento';
import { VeiculoEventoTipo } from '../../dominio/veiculo/veiculo-evento-tipo';
import { Rota } from './rota';
import { EventoTipo } from '../evento/evento-tipo';

export class Viagem extends VeiculoEvento implements Sincrono {

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
        public tipo?: VeiculoEventoTipo,
        public localSaida?: Local,
        public localSaidaDescricao?: string,
        public quilometragemSaida?: number,
        public localChegada?: Local,
        public localChegadaDescricao?: string,
        public quilometragemChegada?: number,
        public pessoa?: Pessoa, // responsavel pelo veiculo
        public unidadeOrganizacional?: UnidadeOrganizacional, // lotacao Veiculo
        public rota?: Rota[],
        public participacaoList?: Participacao[],
        public idServidor?: number,
        public sincronizado?: Date,
        public apagar?: boolean,
    ) {
        super(id, planejamento, recurso, eventoTipo, local, localDescricao, inicio, termino,
            descricao, pai, evidenciaList, VeiculoEventoTipo.VIAGEM);
    }

}
