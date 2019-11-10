import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RegistroPage } from './registro.page';
import { RegistroPageRoutingModule } from './registro-routing.module';
import { ViagemPageModule } from './viagem/viagem.module';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    RegistroPageRoutingModule,
    ViagemPageModule
  ],
  declarations: [RegistroPage],
  providers: [],
})
export class RegistroPageModule {}
