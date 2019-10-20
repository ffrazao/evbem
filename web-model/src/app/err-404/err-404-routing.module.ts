import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Err404Component } from "./err-404.component";

const routes: Routes = [{
    path: '',
    component: Err404Component
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class Err404RoutingModule { }