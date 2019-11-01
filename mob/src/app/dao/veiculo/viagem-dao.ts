import { Injectable } from '@angular/core';
import { SqliteService } from 'src/app/comum/servico/local/sqlite.service';
import { Crud } from '../crud';
import { Viagem } from 'src/app/entidade/veiculo/viagem';

@Injectable({ providedIn: 'root' })
export class ViagemDao implements Crud<Viagem, any> {

    constructor(
        private sqlite: SqliteService
    ) {
    }

    iniciar(r?: Viagem): Viagem {
        throw new Error("Method not implemented.");
    }

    criar(r: Viagem): number {
        throw new Error("Method not implemented.");
    }

    restaurar(id: number): Viagem {
        throw new Error("Method not implemented.");
    }

    atualizar(id: number, r: Viagem) {
        throw new Error("Method not implemented.");
    }

    excluir(id: number): boolean {
        throw new Error("Method not implemented.");
    }

    listar(f: any): [] {
        throw new Error("Method not implemented.");
    }

}
