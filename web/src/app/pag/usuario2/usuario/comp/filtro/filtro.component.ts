import { Component, OnInit, Input, Output } from '@angular/core';

@Component({
  selector: 'usuario-filtro',
  templateUrl: './filtro.component.html',
  styleUrls: ['./filtro.component.scss']
})
export class FiltroComponent implements OnInit {

  @Input() @Output() private dados;

  constructor() { }

  ngOnInit() {
  }

}
