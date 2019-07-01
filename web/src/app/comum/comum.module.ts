import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxViacepModule } from '@brunoc/ngx-viacep';
import { ErrorDialogComponent } from './componente/error-dialog/errordialog.component';
import { ErrorDialogService } from './componente/error-dialog/errordialog.service';
import { DomService } from './servico/dom.service';
import { MirrorComponent } from './componente/mirror/mirror.component';
import { AuthGuardService } from './guard/auth-guard.service';
import { UsuarioLoginComponent } from './componente/usuario-login/usuario-login.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [ErrorDialogComponent, MirrorComponent, UsuarioLoginComponent],
  imports: [
    CommonModule,
    NgxViacepModule,
    RouterModule
  ],
  exports: [UsuarioLoginComponent],
  providers: [DomService, ErrorDialogService, AuthGuardService],
  entryComponents: [ErrorDialogComponent, MirrorComponent],
})
export class ComumModule { }
