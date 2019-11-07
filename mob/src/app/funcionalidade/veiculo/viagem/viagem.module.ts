import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ViagemPage } from './viagem.page';
import { ViagemPageRoutingModule } from './viagem-routing.module';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    ViagemPageRoutingModule,
  ],
  declarations: [ViagemPage],
  providers: [],
})
export class ViagemPageModule {}
