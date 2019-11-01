export class Local {

    constructor(
        public id?: number,
        public longitude?: number,
        public latitude?: number,
        public altitude?: number,
        public velocidade?: number,
        public momento?: Date,
        public descricao?: string,
    ) {
        altitude = altitude === null ? 0 : altitude;
        velocidade = velocidade === null ? 0 : velocidade;
        momento = momento === null ? new Date() : momento;
        descricao = descricao === null ? '' : descricao;
    }

}
