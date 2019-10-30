import { Combustivel } from './combustivel';
import { VeiculoTipo } from './veiculo-tipo';
import { VeiculoMarca } from './veiculo-marca';
import { VeiculoModelo } from './veiculo-modelo';

export class Veiculo {
    id: number;
    tipo: VeiculoTipo;
    marca: VeiculoMarca;
    modelo: VeiculoModelo;
    siglaProprietario: string;
    identificacaoPatrimonial: string;
    anoFabricação: number;
    anoModelo: number;
    combustivel: Combustivel;
    placa: string;
}
