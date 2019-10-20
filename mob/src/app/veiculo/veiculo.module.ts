import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { VeiculoPageRoutingModule } from './veiculo.router.module';

import { VeiculoPage } from './veiculo.page';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    VeiculoPageRoutingModule
  ],
  declarations: [VeiculoPage]
})
export class VeiculoPageModule {}
