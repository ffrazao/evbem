import { Injectable } from '@angular/core';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite/ngx';

@Injectable({ providedIn: 'root' })
export class SqliteService {

    private db: SQLiteObject;

    constructor(private sqlite: SQLite) {
    }

    getDb() {
        console.log(0);
        if (this.db == null) {
            console.log(1);
            (async () => {
                console.log(2);
                this.db = await this.sqlite.create({ name: 'data.db', location: 'default' });
                console.log(3);
            })();
            console.log(4);
        }
        console.log(5);
        return this.db;
    }

    public preparaDatabase(): void {
        try {
            this.preparaTabelas(this.getDb());
            console.log('Tabelas locais preparadas');
            this.inserePadrao(this.getDb());
            console.log('Tabelas locais iniciadas');
        } catch (e) {
            alert(`Erro ao preparar tabelas locais ${JSON.stringify(e)}`);
        }
    }

    private async preparaTabelas(db: SQLiteObject) {
        console.log('db criado?', db);
        await db.sqlBatch([
            [`DROP TABLE IF EXISTS veiculo`],
            [`DROP TABLE IF EXISTS evento`],
            [`CREATE TABLE IF NOT EXISTS veiculo (
                id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
                placa TEXT,
            )`],
            [`CREATE TABLE IF NOT EXISTS evento (
                id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
                nome TEXT,
            )`],
        ]);
    }

    private async inserePadrao(db: SQLiteObject) {
        await db.executeSql(`SELECT COUNT(*) AS qtd FROM veiculo`)
            .then(async (data: any) => {
                if (data.rows.item(0).qtd === 0) {
                    await this.insereBatch(db, [
                        [`INSERT INTO veiculo (placa) VALUES (?)`, [`JKO-5353`]],
                        [`INSERT INTO veiculo (placa) VALUES (?)`, [`JFR-8989`]],
                    ], 'Veiculos padrão inseridos', 'Erro ao inserir Veiculos padrão');
                }
            });

        await db.executeSql(`SELECT COUNT(*) AS qtd FROM evento`)
            .then(async (data: any) => {
                if (data.rows.item(0).qtd === 0) {
                    await this.insereBatch(db, [
                        [`INSERT INTO evento (nome) VALUES (?)`, [`Primeiro Evento`]],
                        [`INSERT INTO evento (nome) VALUES (?)`, [`Segundo Evento`]],
                    ], 'Eventos padrão inseridos', 'Erro ao inserir Eventos padrão');
                }
            });
    }

    private insereBatch(db: SQLiteObject, instrucoes: any[], msgSucesso, msgErro) {
        db.sqlBatch(instrucoes)
            .then(() => console.log(msgSucesso))
            .catch(e => alert(`${msgErro} [${JSON.stringify(e)}]`));
    }

}
