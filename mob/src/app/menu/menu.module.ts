import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { MenuPage } from './menu.page';
import { MenuRoutingModule } from './menu-routing.module';
import { MenuUsuarioComponentModule } from '../comum/componente/menu-usuario/menu-usuario.module';
import { MenuUsuarioComponent } from '../comum/componente/menu-usuario/menu-usuario.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MenuRoutingModule,
    MenuUsuarioComponentModule,
  ],
  declarations: [MenuPage]
})
export class MenuPageModule {}
