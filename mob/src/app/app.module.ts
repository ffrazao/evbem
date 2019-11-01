import { NgModule } from '@angular/core';
import { BrowserModule, HAMMER_GESTURE_CONFIG } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { SQLite } from '@ionic-native/sqlite/ngx';
import { SQLitePorter } from '@ionic-native/sqlite-porter/ngx';
import { File } from '@ionic-native/file/ngx';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { SqliteService } from './comum/servico/local/sqlite.service';
import { LoginService } from './comum/componente/login/login.service';
import { MensagemService } from './comum/servico/mensagem/mensagem.service';
import { AutInterceptorProvider } from './comum/servico/seguranca/interceptor/aut-interceptor';
import { AuthGuard } from './comum/servico/seguranca/guard-route/auth-guard.service';
import { IonicGestureConfig } from './Ionic-gesture-config';

@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    IonicModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  exports: [
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    StatusBar,
    SplashScreen,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    SQLite,
    SQLitePorter,
    File,
    {provide: HAMMER_GESTURE_CONFIG, useClass: IonicGestureConfig},
    SqliteService,
    MensagemService,
    LoginService,
    AutInterceptorProvider,
    AuthGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
