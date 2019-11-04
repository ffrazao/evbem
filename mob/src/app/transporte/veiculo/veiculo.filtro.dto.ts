import { FiltroDto } from 'src/app/comum/transporte/filtro';
import { Combustivel } from 'src/app/dominio/veiculo/combustivel';

export class VeiculoFiltroDto extends FiltroDto {

    constructor(
        public pesq?: string[],
        public produtoTipo?: string[],
        public marca?: string[],
        public modelo?: string[],
        public numeroSerie?: string[],
        public renavan?: string[],
        public anoFabricacao?: number[],
        public anoModelo?: number[],
        public cor?: string[],
        public combustivel?: Combustivel[],
        public placa?: string[],
        public identificacaoPatrimonial?: string[],
    ) {
        super();
    }
}
