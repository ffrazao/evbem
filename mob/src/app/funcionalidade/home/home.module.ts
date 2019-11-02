import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { HomePage } from './home.page';
import { HomeRoutingModule } from './home-routing.module';
import { MenuUsuarioComponentModule } from '../../comum/componente/menu-usuario/menu-usuario.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    HomeRoutingModule,
    MenuUsuarioComponentModule,
  ],
  declarations: [HomePage]
})
export class HomePageModule {}
