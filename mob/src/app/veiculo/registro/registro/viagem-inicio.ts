import { Veiculo } from 'src/app/entidade/veiculo/veiculo';
import { Pessoa } from 'src/app/entidade/pessoa/pessoa';
import { Local } from 'src/app/entidade/comum/local';
import { UnidadeOrganizacional } from 'src/app/entidade/funcional/unidade-organizacional';

export class ViagemInicio {
    veiculo: Veiculo;
    dia = '2019-10-29';
    condutor: Pessoa;
    localSaida: Local;
    hora = '22:00';
    odometro = 0;
    responsavelVeiculo: Pessoa;
    lotacaoVeiculo: UnidadeOrganizacional;
}
