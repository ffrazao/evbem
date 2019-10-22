import { ApiService } from './api.service';
import { HttpClient } from '@angular/common/http';
import { Filtro } from '../../entidade/filtro';

export abstract class CrudService<T, F extends Filtro> extends ApiService {

    constructor(protected http: HttpClient) {
        super(http);
    }

    public abstract funcionalidade(): string;

    public iniciar(r: T) {
        return this.http.get(`${this.url}/${this.funcionalidade()}/iniciar`, r);
    }

    public criar(r: T) {
        return this.http.post(`${this.url}/${this.funcionalidade()}`, r);
    }

    public restaurar(id: number) {
        return this.http.get(`${this.url}/${this.funcionalidade()}/${id}`);
    }

    public atualizar(id: number, r: T) {
        return this.http.post(`${this.url}/${this.funcionalidade()}/${id}`, r);
    }

    public excluir(id: number) {
        return this.http.delete(`${this.url}/${this.funcionalidade()}/${id}`);
    }

    public listar(f: F) {
        return this.http.get(`${this.url}/${this.funcionalidade()}`, f);
    }

}
