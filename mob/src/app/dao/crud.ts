export interface Crud<T, F> {

    iniciar(r?: T): T;

    criar(r: T): number;

    restaurar(id: number): T;

    atualizar(id: number, r: T);

    excluir(id: number): boolean;

    listar(f: F): [];

}
