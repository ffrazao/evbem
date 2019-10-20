import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Crud1Component } from "./crud-1.component";
import { ListComponent } from './list/list.component';
import { FormComponent } from './form/form.component';

const routes: Routes = [{
    path: '',
    component: Crud1Component,
    children: [
        { path: 'novo', component: FormComponent },
        { path: ':id', component: FormComponent },
        { path: '', component: ListComponent },
    ]
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class Crud1RoutingModule { }