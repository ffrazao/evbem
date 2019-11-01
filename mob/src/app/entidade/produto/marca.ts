export class Marca {

    constructor(
        public id?: number,
        public nome?: string,
        public pai?: Marca,
    ) {

    }

}
