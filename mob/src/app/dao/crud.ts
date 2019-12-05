import { Observable } from 'rxjs';
import { FiltroDto } from '../comum/transporte/filtro';

export interface Crud<E, F extends FiltroDto, R> {

    // ALTERAR : "alterar";
    
    // CRIAR : "criar";
    
    // EXCLUIR : "excluir";
    
    // INICIAR : "iniciar";
    
    // LISTAR : "listar";
    
    // RESTAURAR : "restaurar";
    
	// SALVAR : "salvar";

    alterar(entidades: {id: number, entidade: E}[]): Observable<{id: number, entidade: E}[]>;
    
    criar(entidades: E[]): Observable<E[]>;
    
    excluir(ids: number[]): Observable<{id: number, resultado: boolean}[]>;
    
    iniciar(modelo?: Map<string, string>): Observable<E>;
    
    listar(filtro?: F): Observable<R[]>;
    
    restaurar(ids: number[]): Observable<E[]>;
    
    salvar(entidades: E[]): Observable<E[]>;

}
