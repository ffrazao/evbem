import { Injectable } from '@angular/core';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite/ngx';

@Injectable({ providedIn: 'root' })
export class SqliteService {

    constructor(private sqlite: SQLite) {
    }

    getDb() {
        return this.sqlite.create({ name: 'data.db', location: 'default' });
    }

    public createDatabase() {
        return this.getDb().then((db: SQLiteObject) => {
            this.preparaTabelas(db);
        }).catch(e => alert(`Erro ao preparar tabelas locais ${JSON.stringify(e)}`));
    }

    private preparaTabelas(db: SQLiteObject) {
        db.sqlBatch([
            [`CREATE TABLE IF NOT EXISTS veiculo (
                id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
                placa TEXT,
            )`],
            [`CREATE TABLE IF NOT EXISTS evento (
                id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
                nome TEXT,
            )`],
        ])
            .then(() => {
                console.log('Tabelas locais preparadas');
                this.inserePadrao(db);
            })
            .catch(e => alert(`Erro ao preparar tabelas locais [${JSON.stringify(e)}]`));
    }

    private inserePadrao(db: SQLiteObject) {
        db.executeSql(`SELECT COUNT(*) AS qtd FROM veiculo`)
            .then((data: any) => {
                if (data.rows.item(0).qtd === 0) {
                    this.insereBatch(db, [
                        [`INSERT INTO veiculo (placa) VALUES (?)`, [`JKO-5353`]],
                        [`INSERT INTO veiculo (placa) VALUES (?)`, [`JFR-8989`]],
                    ], 'Veiculos padrão inseridos', 'Erro ao inserir Veiculos padrão');
                }
            })
            .catch(e => alert(`Erro ao inserir valores padrão [${JSON.stringify(e)}]`));
        db.executeSql(`SELECT COUNT(*) AS qtd FROM evento`)
            .then((data: any) => {
                if (data.rows.item(0).qtd === 0) {
                    this.insereBatch(db, [
                        [`INSERT INTO evento (nome) VALUES (?)`, [`Primeiro Evento`]],
                        [`INSERT INTO evento (nome) VALUES (?)`, [`Segundo Evento`]],
                    ], 'Eventos padrão inseridos', 'Erro ao inserir Eventos padrão');
                }

            })
            .catch(e => alert(`Erro ao inserir valores padrão [${JSON.stringify(e)}]`));
    }

    private insereBatch(db: SQLiteObject, instrucoes: any[], msgSucesso, msgErro) {
        db.sqlBatch(instrucoes)
            .then(() => console.log(msgSucesso))
            .catch(e => alert(`${msgErro} [${JSON.stringify(e)}]`));
    }

}
