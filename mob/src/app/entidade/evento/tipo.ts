export class Tipo {

    constructor(
        public id?: number,
        public codigo?: string,
        public nome?: string,
        public pai?: Tipo,
        public recurso_tipo?: string,
    ) {
    }

}