import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { Crud } from '../../crud';
import { Veiculo } from '../../../entidade/veiculo/veiculo';
import { VeiculoFiltroDto } from 'src/app/transporte/veiculo/veiculo.filtro.dto';

const funcionalidade = 'veiculo';

@Injectable({ providedIn: 'root' })
export class VeiculoDao implements Crud<Veiculo, VeiculoFiltroDto, Veiculo> {

    constructor(
        private http: HttpClient
    ) {
    }

    iniciar(modelo?: Map<string, string>): Observable<Veiculo> {
        const query = `?`;
        return this.http.get<Veiculo>(`${environment.apiUrl}/${funcionalidade}/iniciar${query}`,
            // { params:  }
        );
    }

    criar(entidades: Veiculo[]): Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }

    restaurar(ids: number[]): Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }
    alterar(entidades: { id: number; entidade: Veiculo; }[]): Observable<{ id: number; entidade: Veiculo; }[]> {
        throw new Error('Method not implemented.');
    }
    excluir(ids: number[]): Observable<{ id: number; resultado: boolean; }[]> {
        throw new Error('Method not implemented.');
    }
    listar(filtro: VeiculoFiltroDto): Observable<Veiculo[]> {
        const query = `?pesq=${filtro.pesq}`;
        return this.http.get<Veiculo[]>(`${environment.apiUrl}/${funcionalidade}${query}`,
            // { params:  }
        );
    }
    salvar(entidades: Veiculo[]): Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }

}
