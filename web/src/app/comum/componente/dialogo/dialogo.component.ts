import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialogo',
  templateUrl: './dialogo.component.html',
  styleUrls: ['./dialogo.component.scss']
})
export class DialogoComponent {

  public acao = null;

  constructor(
    public dialogRef: MatDialogRef<DialogoComponent>,
    @Inject(MAT_DIALOG_DATA) public mensagem: string
  ) {
  }

  onOkClick() {
    console.log('Ok...');
    this.acao = true;
    this.dialogRef.close(this.acao);
  }

  onCancelarClick() {
    console.log('cancelar...');
    this.acao = false;
    this.dialogRef.close(this.acao);
  }

}