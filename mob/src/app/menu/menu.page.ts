import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-menu',
  templateUrl: 'menu.page.html',
  styleUrls: ['menu.page.scss']
})
export class MenuPage implements OnInit, OnDestroy {

  private inscricao: any;

  public paginas: Pagina[];

  constructor(
    private service: LoginService,
    private router: Router
  ) {
    this.paginas = [
      new Pagina('Home', '/m/home', 'home'),
      new Pagina('Login', '/login', 'key', false),
      new Pagina('Veículo', '/m/s/veiculo', 'car'),
      new Pagina('List (Não Seguro)', '/list', 'list'),
      new Pagina('List (Seguro)', '/s/list', 'list'),
      new Pagina('', '', ''),
      new Pagina('Sair', () => this.service.logout(), 'key', true),
    ];
  }

  ngOnInit() {
    this.inscricao = this.service.usuarioLogado.subscribe((r) => {
      const estaLogado = r != null;
      this.paginas.forEach(p => {
        if (p.titulo === 'Login') {
          p.invisivel = estaLogado;
        }
        if (p.titulo === 'Sair' || p.titulo === '') {
          p.invisivel = !estaLogado;
        }
      });
    });
  }

  ngOnDestroy() {
    this.inscricao.unsubscribe();
  }

  public executar(pagina: Pagina) {
    if (typeof pagina.url === 'string' || pagina.url instanceof String) {
      if (pagina.url.length) {
        this.router.navigateByUrl(pagina.url as string);
      }
    } else {
      pagina.url();
    }
  }

  public filtra(paginas: Pagina[]) {
    return paginas.filter((p) => !p.invisivel);
  }

}

class Pagina {
  constructor(
    public titulo: string,
    public url: any,
    public icone: string,
    public invisivel?: boolean
  ) {
  }
}
