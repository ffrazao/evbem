import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

import { StaticInjectorService } from './app/comum/ferramenta/static-injector-service';

import { } from 'hammerjs';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule).then((moduleRef) => {
  StaticInjectorService.injector = moduleRef.injector;
}).catch(err => console.log(err));
