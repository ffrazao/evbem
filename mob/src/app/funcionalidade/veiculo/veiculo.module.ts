import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { VeiculoPageRoutingModule } from './veiculo-routing.module';

import { VeiculoPage } from './veiculo.page';

@NgModule({
  declarations: [VeiculoPage],
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    VeiculoPageRoutingModule
  ],
})
export class VeiculoPageModule {}
