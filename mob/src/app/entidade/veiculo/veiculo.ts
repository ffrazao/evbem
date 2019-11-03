import { Combustivel } from '../../dominio/veiculo/combustivel';
import { Produto } from '../principal/produto';

export class Veiculo {

    constructor(
        public id?: number,
        public produto?: Produto,
        public placa?: string,
        public anoFabricacao?: number,
        public anoModelo?: number,
        public renavan?: string,
        public combustivel?: Combustivel[],
        public cor?: string,
        public corRgb?: string,
    ) {
    }

}
