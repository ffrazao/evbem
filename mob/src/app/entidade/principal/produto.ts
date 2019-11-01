import { Modelo } from "../produto/modelo";
import { Marca } from "../produto/marca";
import { Pessoa } from "./pessoa";
import { Recurso } from "./principal";
import { RecursoTipo } from "../../dominio/principal/recurso-tipo";
import { BemPatrimonial } from '../produto/bem-patrimonial';

export class Produto extends Recurso {

    constructor(
        public id?: number,
        public observacao?: string,
        public modelo?: Modelo,
        public marca?: Marca,
        public numeroSerie?: string,
        public pessoa?: Pessoa,
        public bemPatrimonial?: BemPatrimonial
    ) {
        super(id, RecursoTipo.PRODUTO, observacao);
    }

}
