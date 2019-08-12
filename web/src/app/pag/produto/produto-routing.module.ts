import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TabComponent } from './comp/tab/tab.component';
import { FormComponent } from './comp/form/form.component';
import { ResCriarService } from './servico/res-criar.service';
import { ResVerService } from './servico/res-ver.service';
import { ResListarService } from './servico/res-listar.service';

const routes: Routes = [
  {
    path: '',
    component: TabComponent,
    resolve: { tabela: ResListarService },
    data: {},
    runGuardsAndResolvers: 'always'
  },
  {
    path: 'n',
    component: FormComponent,
    resolve: { formulario: ResCriarService },
    data: {}
  },
  {
    path: ':id',
    component: FormComponent,
    resolve: { formulario: ResVerService },
    data: {}
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProdutoRoutingModule { }
