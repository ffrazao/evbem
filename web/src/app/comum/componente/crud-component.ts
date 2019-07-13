export abstract class CrudComponent {

  protected ids;
  protected pos;
  protected novaPos;
  
  constructor(
    protected _urlPrincipal: string[]
  ) {
  }
  
  get id() {
    return (!this.ids || !Array.isArray(this.ids)) ? null : this.ids[this.pos];
  }

  public vaiParaPrimeiro(): void {
    this.vaiPara(0);
  }

  public vaiParaAnterior(): void {
    this.vaiPara(this.pos - 1);
  }

  public vaiParaProximo(): void {
    this.vaiPara(this.pos + 1);
  }

  public vaiParaUltimo(): void {
    this.vaiPara(this.ids.length - 1);
  }

  abstract vaiPara(pos: number);

  protected setPos(vlr: number) {
    if (vlr < 0 || !this.ids || !this.ids.length) {
      vlr = 0;
    } else if (vlr >= this.ids.length) {
      vlr = this.ids.length - 1;
    }
    this.pos = vlr;
  }

}