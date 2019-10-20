import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PararComponent } from './parar.component';

const routes: Routes = [
    {
        path: '',
        component: PararComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PararComponentRoutingModule { }