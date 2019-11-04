export class ProdutoTipo {

    constructor(
        public id?: number,
        public codigo?: string,
        public nome?: string,
        public pai?: ProdutoTipo,
    ) {

    }

}
