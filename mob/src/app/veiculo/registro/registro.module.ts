import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Geolocation } from '@ionic-native/geolocation/ngx';

import { RegistroPage } from './registro.page';
import { RegistroPageRoutingModule } from './registro-routing.module';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    RegistroPageRoutingModule,
  ],
  declarations: [RegistroPage],
  providers: [Geolocation],
})
export class RegistroPageModule {}
