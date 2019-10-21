import { Injectable } from '@angular/core';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite/ngx';
import { Filtro } from '../../entidade/filtro';

@Injectable({ providedIn: 'root' })
export class SqliteService<T, F extends Filtro > {

    private db: SQLiteObject = null;

    constructor(private sqlite: SQLite) {
    }

    public getDb(): Promise<SQLiteObject> {
        return new Promise(async (resolve, reject) => {
            if (this.db == null) {
                this.db = await this.sqlite.create({ name: 'data.db', location: 'default' });
            }
            resolve(this.db);
        });
    }

    public preparaDatabase(): Promise<void> {
        return new Promise(async (resolve, reject) => {
            try {
                const database = await this.getDb();
                await this.preparaTabelas(database);
                console.log('Tabelas locais preparadas');
                await this.inserePadrao(database);
                console.log('Tabelas locais iniciadas');
                resolve();
            } catch (e) {
                alert(`Erro ao preparar tabelas locais ${JSON.stringify(e)}`);
            }
        });
    }

    private preparaTabelas(db: SQLiteObject) {
        return new Promise<void>(async (resolve, reject) => {
            await db.sqlBatch([
                [`DROP TABLE IF EXISTS veiculo`],
                [`DROP TABLE IF EXISTS evento`],
                [`CREATE TABLE IF NOT EXISTS veiculo (
                    id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
                    placa TEXT
                )`],
                [`CREATE TABLE IF NOT EXISTS evento (
                    id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
                    nome TEXT
                )`],
            ]);
            resolve();
        });
    }

    private async inserePadrao(db: SQLiteObject) {
        return new Promise<void>(async (resolve, reject) => {
            await db.executeSql(`SELECT COUNT(*) AS qtd FROM veiculo`, []).then(async (data: any) => {
                if (data.rows.item(0).qtd === 0) {
                    await this.insereBatch(db, [
                        [`INSERT INTO veiculo (placa) VALUES (?)`, [`JKO-5353`]],
                        [`INSERT INTO veiculo (placa) VALUES (?)`, [`JFR-8989`]],
                    ], 'Veiculos padrão inseridos', 'Erro ao inserir Veiculos padrão');
                }
            });
            await db.executeSql(`SELECT COUNT(*) AS qtd FROM evento`, []).then(async (data: any) => {
                if (data.rows.item(0).qtd === 0) {
                    await this.insereBatch(db, [
                        [`INSERT INTO evento (nome) VALUES (?)`, [`Primeiro Evento`]],
                        [`INSERT INTO evento (nome) VALUES (?)`, [`Segundo Evento`]],
                    ], 'Eventos padrão inseridos', 'Erro ao inserir Eventos padrão');
                }
            });
            resolve();
        });
    }

    private insereBatch(db: SQLiteObject, instrucoes: any[], msgSucesso, msgErro) {
        db.sqlBatch(instrucoes)
            .then(() => console.log(msgSucesso))
            .catch(e => alert(`${msgErro} [${JSON.stringify(e)}]`));
    }

    public iniciar(r: T) {
        return null;
    }

    public criar(r: T) {
        return null;
    }

    public restaurar(id: number) {
        return null;
    }

    public atualizar(id: number, r: T) {
        return null;
    }

    public excluir(id: number) {
        return null;
    }

    public listar(f: F) {
        return null;
    }

}
