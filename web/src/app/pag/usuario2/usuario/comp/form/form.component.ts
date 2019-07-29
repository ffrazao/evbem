import { Component, OnInit, Input, Output } from '@angular/core';

@Component({
  selector: 'usuario-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  @Input() @Output() private dados;

  constructor() { }

  ngOnInit() {
  }

}
