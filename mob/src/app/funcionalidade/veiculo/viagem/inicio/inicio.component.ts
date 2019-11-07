import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    templateUrl: './inicio.component.html',
    styleUrls: ['./inicio.component.scss']
})
export class InicioComponent {

    constructor(
        private router: Router,
        private route: ActivatedRoute
    ) {
    }

    iniciar() {
        this.router.navigate(['/', 's', 'viagem-registro'], {relativeTo: this.route});
    }

}
