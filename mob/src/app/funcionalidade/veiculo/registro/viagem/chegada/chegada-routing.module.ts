import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ChegadaComponent } from './chegada.component';

const routes: Routes = [
    {
        path: '',
        component: ChegadaComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ChegadaComponentRoutingModule { }
