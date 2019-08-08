import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-visao',
  templateUrl: './visao.component.html',
  styleUrls: ['./visao.component.scss']
})
export class VisaoComponent implements OnInit {

  @Input('tabTitle') title: string;
  @Input() active = false;

  constructor() { }

  ngOnInit() {
  }

}
