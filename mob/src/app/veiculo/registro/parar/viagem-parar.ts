import { Veiculo } from 'src/app/entidade/veiculo/veiculo';
import { Pessoa } from 'src/app/entidade/pessoa/pessoa';
import { Local } from 'src/app/entidade/comum/local';
import { UnidadeOrganizacional } from 'src/app/entidade/funcional/unidade-organizacional';

export class ViagemParar {
    localChegada = new Local(0, 0);
    hora = '22:00';
    odometro = 0;
    servico = '';
}
