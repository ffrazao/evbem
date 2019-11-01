import { Evento } from "./evento";
import { EvidenciaTipo } from "../../dominio/evento/evidencia-tipo";

export class Evidencia {

    constructor(
        public id?: number,
        public evento?: Evento,
        public descricao?: string,
        public conteudo?: string,
        public tipo?: EvidenciaTipo
    ) {
    }
    
}
