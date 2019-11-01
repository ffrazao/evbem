import { Combustivel } from '../../dominio/veiculo/combustivel';
import { Marca } from '../produto/marca';
import { Modelo } from '../produto/modelo';
import { Produto } from '../principal/produto';
import { Pessoa } from '../principal/pessoa';
import { BemPatrimonial } from '../produto/bem-patrimonial';

export class Veiculo extends Produto {

    constructor(
        public id?: number,
        public observacao?: string,
        public modelo?: Modelo,
        public marca?: Marca,
        public numeroSerie?: string,
        public pessoa?: Pessoa,
        public bemPatrimonial?: BemPatrimonial,
        public placa?: string,
        public anoFabricacao?: number,
        public anoModelo?: number,
        public renavan?: string,
        public combustivel?: Combustivel[],
        public cor?: string,
        public corRgb?: string,
    ) {
        super(id, observacao, modelo, marca, numeroSerie, pessoa, bemPatrimonial);
    }

}
