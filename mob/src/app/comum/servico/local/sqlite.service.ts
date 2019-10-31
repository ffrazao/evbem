import { Injectable } from '@angular/core';
import { Platform } from '@ionic/angular';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite/ngx';
import { SQLitePorter } from '@ionic-native/sqlite-porter/ngx';

import { HttpClient } from '@angular/common/http';
import { MensagemService } from '../mensagem/mensagem.service';

export const NomeBancoDadosLocal = 'data.db';
const localScriptInicializacao = 'assets/db/create.sql';

@Injectable({ providedIn: 'root' })
export class SqliteService<T, F> {

    private conexao: SQLiteObject = null;

    constructor(
        private plt: Platform,
        private sqlite: SQLite,
        private sqlitePorter: SQLitePorter,
        private http: HttpClient,
        private mensagem: MensagemService,
    ) {
        this.plt.ready().then(() => {
            this.pegaConexao().then((db: SQLiteObject) => {
                this.carregarBancoDadosLocal(db);
            });
        });
    }

    private carregarBancoDadosLocal(db: SQLiteObject) {
        this.mensagem.aguarde('Abrindo conexão local').then((res) => {
            res.present();
            this.http.get(localScriptInicializacao, { responseType: 'text' }).subscribe(sql => {
                this.sqlitePorter.importSqlToDb(db, sql)
                    .then(() => {
                        console.log('Banco de dados local carregado.');
                        res.dismiss();
                    })
                    .catch(e => {
                        console.error(e);
                        this.mensagem.erro(e);
                        res.dismiss();
                    });
            });
        });
    }

    public get conexaoAtiva(): boolean {
        return this.conexao != null;
    }

    public async pegaConexao() {
        if (!this.conexaoAtiva) {
            this.conexao = await this.sqlite.create({ name: NomeBancoDadosLocal, location: 'default' });
        }
        return this.conexao;
    }

    public async fecharConexao() {
        if (this.conexaoAtiva) {
            await this.conexao.close();
            this.conexao = null;
        }
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
