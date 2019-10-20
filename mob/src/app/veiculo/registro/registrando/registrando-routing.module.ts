import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrandoComponent } from './registrando.component';

const routes: Routes = [
    {
        path: '',
        component: RegistrandoComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RegistrandoComponentRoutingModule { }