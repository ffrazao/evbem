import { Component, OnInit, ContentChildren, QueryList } from '@angular/core';
import { VisaoComponent } from '../visao/visao.component';

@Component({
  selector: 'app-maquina-estado',
  templateUrl: './maquina-estado.component.html',
  styleUrls: ['./maquina-estado.component.scss']
})
export class MaquinaEstadoComponent implements OnInit {

  @ContentChildren(VisaoComponent) visaoList: QueryList<VisaoComponent>;

  constructor() { }

  ngOnInit() {
  }

  ngAfterContentInit() {
    // get all active tabs
    let activeTabs = this.visaoList.filter((tab) => tab.active);

    // if there is no active tab set, activate the first
    if (activeTabs.length === 0) {
      this.selectTab(this.visaoList.first);
    }
  }

  selectTab(visao: VisaoComponent){
    // deactivate all tabs
    this.visaoList.toArray().forEach(visao => visao.active = false);
    
    // activate the tab the user has clicked on.
    visao.active = true;
  }
}
