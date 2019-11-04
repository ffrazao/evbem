import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-escolhe-item',
    templateUrl: 'escolhe-item.component.html',
    styleUrls: ['escolhe-item.component.scss'],
})
export class EscolheItemComponent {

    @Input() items: any[];

    @Input() varios = false;

    constructor() {}

}
