export class Local {

    constructor(
        public longitude: number,
        public latitude: number,
        public altitude?,
        public velocidade?,
        public momento?,
        public descricao?,
    ) {
        altitude = altitude === null ? 0 : altitude;
        velocidade = velocidade === null ? 0 : velocidade;
        momento = momento === null ? new Date() : momento;
        descricao = descricao === null ? '' : descricao;
    }

}
