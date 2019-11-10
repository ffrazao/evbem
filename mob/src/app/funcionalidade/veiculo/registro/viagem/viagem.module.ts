import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ViagemPage } from './viagem.page';
import { ViagemPageRoutingModule } from './viagem-routing.module';
import { ViagemService } from './viagem.service';
import { SaidaComponentModule } from './saida/saida.module';
import { RotaComponentModule } from './rota/rota.module';
import { ChegadaComponentModule } from './chegada/chegada.module';

@NgModule({
  declarations: [ViagemPage],
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    SaidaComponentModule,
    RotaComponentModule,
    ChegadaComponentModule,
    ViagemPageRoutingModule,
  ],
  providers: [ViagemService],
  entryComponents: [ViagemPage],
})
export class ViagemPageModule {}
