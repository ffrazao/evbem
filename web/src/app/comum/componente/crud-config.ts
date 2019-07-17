import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { FormGroup } from '@angular/forms';

@Injectable({ providedIn: 'root' })
export class CrudConfig {

    // variáveis de apoio à tabela
    private _fonteDados: MatTableDataSource<any>;
    private _colunasExibidas: string[];
    private _quantidadeRegistros: number = 0;
    private _tamanhoPagina: number = 10;
    private _tamanhoPaginaOpcoes = [5, 10, 20, 100];
    private _selecaoRegistros: SelectionModel<any>;
    private _selecaoInicial: [];
    private _permitirMultiSelecao: boolean = true;
    private _paginaAtual: number = 0;

    // variáveis de apoio ao formulario
    private _formulario: FormGroup;
    private _pos: number;
    private _novaPos: number;

    constructor(private _urlPrincipal: string[]) { }

    // métodos de apoio à tabela
    get fonteDados() {
        return this._fonteDados;
    }

    set fonteDados(_fonteDados) {
        this._fonteDados = _fonteDados;
    }

    get colunasExibidas() {
        return this._colunasExibidas;
    }

    set colunasExibidas(_colunasExibidas) {
        this._colunasExibidas = _colunasExibidas;
    }

    get quantidadeRegistros() {
        return this._quantidadeRegistros;
    }

    set quantidadeRegistros(_quantidadeRegistros) {
        this._quantidadeRegistros = _quantidadeRegistros;
    }

    get tamanhoPagina() {
        return this._tamanhoPagina;
    }

    set tamanhoPagina(_tamanhoPagina) {
        this._tamanhoPagina = _tamanhoPagina;
    }

    get tamanhoPaginaOpcoes() {
        return this._tamanhoPaginaOpcoes;
    }

    set tamanhoPaginaOpcoes(_tamanhoPaginaOpcoes) {
        this._tamanhoPaginaOpcoes = _tamanhoPaginaOpcoes;
    }

    get selecaoRegistros() {
        return this._selecaoRegistros;
    }

    set selecaoRegistros(_selecaoRegistros) {
        this._selecaoRegistros = _selecaoRegistros;
    }

    get selecaoInicial() {
        return this._selecaoInicial;
    }

    set selecaoInicial(_selecaoInicial) {
        this._selecaoInicial = _selecaoInicial;
    }

    get permitirMultiSelecao() {
        return this._permitirMultiSelecao;
    }

    set permitirMultiSelecao(_permitirMultiSelecao) {
        this._permitirMultiSelecao = _permitirMultiSelecao;
    }

    get paginaAtual() {
        return this._paginaAtual;
    }

    set paginaAtual(_paginaAtual) {
        this._paginaAtual = _paginaAtual;
    }

    // métodos de apoio ao formulario
    public get formulario(): FormGroup {
        return this._formulario;
    }

    public set formulario(_formulario: FormGroup) {
        this._formulario = _formulario;
    }

    get id(): number {
        return (!this.ids || !Array.isArray(this.ids) || isNaN(this.pos) || this.pos < 0 || this.pos >= this.ids.length) ?
            null :
            (this.ids[this.pos].id ? this.ids[this.pos].id : this.ids[this.pos]);
    }

    get novaPos(): number {
        return this._novaPos ? this._novaPos : 1;
    }

    set novaPos(_novaPos: number) {
        this._novaPos = _novaPos;
    }

    get pos(): number {
        return this._pos ? this._pos : 0;
    }

    set pos(_pos: number) {
        if (!_pos || isNaN(_pos) || _pos < 0) {
            _pos = 0;
        } else if (this.ids && this.ids.length && _pos >= this.ids.length) {
            _pos = this.ids.length - 1;
        }
        this._pos = _pos;
    }

    get ids(): any[] {
        return this.selecaoRegistros && !this.selecaoRegistros.isEmpty() ? this.selecaoRegistros.selected : this.fonteDados && this.fonteDados.data ? this.fonteDados.data : [];
    }

    get urlPrincipal(): string[] {
        return this._urlPrincipal;
    }

    set urlPrincipal(_urlPrincipal: string[]) {
        this._urlPrincipal = _urlPrincipal;
    }

    public vaiParaPrimeiro(): string[] {
        return this.vaiPara(0);
    }

    public vaiParaAnterior(): string[] {
        return this.vaiPara(this.pos - 1);
    }

    public vaiParaProximo(): string[] {
        return this.vaiPara(this.pos + 1);
    }

    public vaiParaUltimo(): string[] {
        return this.vaiPara(this.ids.length - 1);
    }

    public vaiPara(_pos: number): string[] {
        this.pos = _pos;

        let result = this.urlPrincipal.slice(0);
        result.push(this.id.toString());

        return result;
    }

}