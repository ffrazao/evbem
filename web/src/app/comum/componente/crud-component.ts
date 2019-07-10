export class CrudComponent {

  // seleçao
  protected sub;
  protected pag: string;
  protected ids: number[];
  protected pos: number;
  protected novaPos: number;

  constructor(
    protected _urlPrincipal: string[]
  ) {
  }

  // ngOnInit(): void {
    // this.sub = this._activatedRoute.paramMap.subscribe(params => {
    //   console.log(12);
    //   let temp: string;
    //   temp = params.get('pag');
    //   this.pag = temp;
    //   temp = params.get('ids');
    //   if (!(/n/i).test(temp)) {
    //     try {
    //       this.ids = temp.split(',').map(e => Number.parseInt(e, 10));
    //       temp = params.get('pos');
    //       this.setPos(Number.parseInt(temp, 10));
    //     } catch (e) {
    //     }
    //   }
    //   if (!this.ids) {
    //     this._router.navigate(this._urlPrincipal);
    //   }
    //   if (!this.pos) {
    //     this.setPos(0);
    //   }
    //   this.novaPos = this.pos + 1;
    // });
  // }

  
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

  public vaiPara(pos): void {
    pos = ("" + pos).trim();
    if ((pos || pos == 0) && !isNaN(pos)) {
      let url = this._urlPrincipal.slice(0);
      url.push(this.ids.join());
      url.push(pos);
      //this._router.navigate(url);
    }
  }

  protected setPos(vlr: number) {
    if (vlr < 0 || !this.ids || !this.ids.length) {
      vlr = 0;
    } else if (vlr >= this.ids.length) {
      vlr = this.ids.length - 1;
    }
    this.pos = vlr;
  }

}