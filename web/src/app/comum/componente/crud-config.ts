import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class CrudConfig {

    private _ids : number[];
    private _pos : number;
    private _novaPos : number;
    private _urlPrincipal: string[];

    constructor (
        private _router: Router
    ) {
    }
   
    get id() : number {
        return (!this._ids || !Array.isArray(this._ids) || isNaN(this._pos) || this._pos < 0 || this._pos >= this._ids.length) ? 
            null : 
            this._ids[this._pos];
    }

    get novaPos() : number {
        return this._novaPos;
    }

    set novaPos(novaPos: number) {
        this._novaPos = novaPos;
    }

    get pos() : number {
        return this._pos;
    }

    set pos(pos: number) {
        if (!pos || isNaN(pos) || pos < 0) {
            pos = 0;
        } else if (this._ids && this._ids.length && pos >= this._ids.length) {
            pos = this._ids.length - 1;
        }
        this._pos = pos;
    }

    get ids(): number[] {
        return this._ids;
    }

    set ids(ids: number[]) {
        this._ids = ids;
    }

    get urlPrincipal(): string[] {
        return this._urlPrincipal;
    }

    set urlPrincipal(urlPrincipal: string[]) {
        this._urlPrincipal = urlPrincipal;
    }    

    public vaiParaPrimeiro(): void {
        this.vaiPara(0);
    }

    public vaiParaAnterior(): void {
        this.vaiPara(this._pos - 1);
    }

    public vaiParaProximo(): void {
        this.vaiPara(this._pos + 1);
    }

    public vaiParaUltimo(): void {
        this.vaiPara(this._ids.length - 1);
    }

    public vaiPara(pos): void {
        if ((pos || pos == 0) && !isNaN(pos)) {
          this.pos = parseInt(pos);
          let url = this._urlPrincipal.slice(0);
          url.push("" + this._ids[this._pos]);
          this._router.navigate(url);
        }
    }

}