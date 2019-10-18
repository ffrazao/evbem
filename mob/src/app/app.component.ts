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
      url: '/veiculo',
      icon: 'car'
    },
  ];

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private sqliteService: SqliteService
  ) {
    this.initializeApp();
  }

  initializeApp() {
    console.log(3342);
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.sqliteService.preparaDatabase();
      this.splashScreen.hide();
    });
  }
}
