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
    private _tamanhoPagina: number = 5;
    private _tamanhoPaginaOpcoes = [5, 10, 20, 100];
    private _selecaoRegistros: SelectionModel<any>;
    private _selecaoInicial: [];
    private _permitirMultiSelecao: boolean = true;
    private _paginaAtual: number = 0;

    // variáveis de apoio ao formulario
    private _formulario: FormGroup;
    private _formularioOriginal: FormGroup;
    private _pos: number;
    private _novaPos: number;
    private _permitirEdicao = false;

    constructor(private _urlPrincipal: string[]) { }

    // métodos de apoio à tabela
    public get fonteDados() {
        return this._fonteDados;
    }

    public set fonteDados(_fonteDados) {
        this._fonteDados = _fonteDados;
    }

    public get colunasExibidas() {
        return this._colunasExibidas;
    }

    public set colunasExibidas(_colunasExibidas) {
        this._colunasExibidas = _colunasExibidas;
    }

    public get quantidadeRegistros() {
        return this._quantidadeRegistros;
    }

    public set quantidadeRegistros(_quantidadeRegistros) {
        this._quantidadeRegistros = _quantidadeRegistros;
    }

    public get tamanhoPagina() {
        return this._tamanhoPagina;
    }

    public set tamanhoPagina(_tamanhoPagina) {
        this._tamanhoPagina = _tamanhoPagina;
    }

    public get tamanhoPaginaOpcoes() {
        return this._tamanhoPaginaOpcoes;
    }

    public set tamanhoPaginaOpcoes(_tamanhoPaginaOpcoes) {
        this._tamanhoPaginaOpcoes = _tamanhoPaginaOpcoes;
    }

    public get selecaoRegistros() {
        return this._selecaoRegistros;
    }

    public set selecaoRegistros(_selecaoRegistros) {
        this._selecaoRegistros = _selecaoRegistros;
    }

    public get selecaoInicial() {
        return this._selecaoInicial;
    }

    public set selecaoInicial(_selecaoInicial) {
        this._selecaoInicial = _selecaoInicial;
    }

    public get permitirMultiSelecao() {
        return this._permitirMultiSelecao;
    }

    public set permitirMultiSelecao(_permitirMultiSelecao) {
        this._permitirMultiSelecao = _permitirMultiSelecao;
    }

    public get paginaAtual() {
        return this._paginaAtual;
    }

    public set paginaAtual(_paginaAtual) {
        this._paginaAtual = _paginaAtual;
    }

    // métodos de apoio ao formulario
    public get formulario(): FormGroup {
        return this._formulario;
    }

    public set formulario(_formulario: FormGroup) {
        this._formulario = _formulario;
    }

    public get formularioOriginal(): FormGroup {
        return this._formularioOriginal;
    }

    public set formularioOriginal(_formularioOriginal: FormGroup) {
        this._formularioOriginal = _formularioOriginal;
    }

    public get id(): number {
        return (!this.ids || !Array.isArray(this.ids) || isNaN(this.pos) || this.pos < 0 || this.pos >= this.ids.length) ?
            null :
            (this.ids[this.pos].id ? this.ids[this.pos].id : this.ids[this.pos]);
    }

    public get novaPos(): number {
        return this._novaPos ? this._novaPos : 1;
    }

    public set novaPos(_novaPos: number) {
        this._novaPos = _novaPos;
    }

    public get pos(): number {
        return this._pos ? this._pos : 0;
    }

    public set pos(_pos: number) {
        if (!_pos || isNaN(_pos) || _pos < 0) {
            _pos = 0;
        } else if (this.ids && this.ids.length && _pos >= this.ids.length) {
            _pos = this.ids.length - 1;
        }
        this._pos = _pos;
    }

    public get permitirEdicao(): boolean {
        return this._permitirEdicao;
    }
    public set permitirEdicao(_permitirEdicao: boolean) {
        this._permitirEdicao = _permitirEdicao;
    }

    public get ids(): any[] {
        return this.selecaoRegistros && !this.selecaoRegistros.isEmpty() ? this.selecaoRegistros.selected : this.fonteDados && this.fonteDados.data ? this.fonteDados.data : [];
    }

    public get urlPrincipal(): string[] {
        return this._urlPrincipal;
    }

    public set urlPrincipal(_urlPrincipal: string[]) {
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