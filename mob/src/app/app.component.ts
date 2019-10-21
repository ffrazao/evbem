import { Component } from '@angular/core';

import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';

import { SqliteService } from './comum/servico/local/sqlite.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent {
  public appPages = [
    {
      title: 'Login',
      url: '/login',
      icon: 'key'
    },
    {
      title: 'Veículo',
      url: '/s/veiculo',
      icon: 'car'
    },
    {
      title: 'Home',
      url: '/s/home',
      icon: 'home'
    },
    {
      title: 'List',
      url: '/s/list',
      icon: 'list'
    }
  ];

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private sqliteService: SqliteService<any, any>
  ) {
    this.initializeApp();
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.sqliteService.preparaDatabase().then(() => {
        this.splashScreen.hide();
      }).catch(e => {
        this.splashScreen.hide();
      });
    });
  }
}
