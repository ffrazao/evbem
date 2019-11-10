import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RotaComponent } from './rota.component';

const routes: Routes = [
    {
        path: '',
        component: RotaComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RotaComponentRoutingModule { }
