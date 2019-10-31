import { Component, OnInit, ViewChild } from '@angular/core';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Router, ActivatedRoute } from '@angular/router';
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import { Map, tileLayer, marker, icon } from 'leaflet';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MensagemService } from 'src/app/comum/servico/mensagem/mensagem.service';
import { RegistroService } from './registro.service';
import { ViagemInicio } from './viagem-inicio';
import { posicaoEmater } from 'src/app/comum/ferramenta/funcao';
import { SqliteService } from 'src/app/comum/servico/local/sqlite.service';

@Component({
    templateUrl: './registro.component.html',
    styleUrls: ['./registro.component.scss']
})
export class RegistroComponent implements OnInit {

    private form: FormGroup;

    @ViewChild('focaliza', { static: false })
    private focaliza;

    private map = null;

    private customMarkerIcon = null;

    private posicao: Geoposition = posicaoEmater();

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private service: RegistroService,
        private formBuilder: FormBuilder,
        private mensagem: MensagemService,
        private geo: Geolocation,
        private barcodeScanner: BarcodeScanner,
        private servicoLocal: SqliteService<any, any>
    ) {
    }

    async ngOnInit() {
        try {
            this.form = this.createForm(new ViagemInicio(), 'JKO5353', 'Fernando Frazão da Silva');
            this.initMap();
            console.log('captando posição atual');
            this.posicao = await this.geo.getCurrentPosition() as Geoposition;
        } finally {
            console.log(this.posicao);
            this.initMap();
        }
    }

    ionViewDidEnter() {
        setTimeout(() => {
            this.focaliza.setFocus();
        }, 500);
    }

    private createForm(viagem: ViagemInicio, pesquisaVeiculo: string, pesquisaCondutor: string): FormGroup {
        return this.formBuilder.group({
            pesquisaVeiculo: [pesquisaVeiculo, [Validators.required]],
            veiculo: [viagem.veiculo, [Validators.required]],
            dia: [viagem.dia, [Validators.required]],
            pesquisaCondutor: [pesquisaCondutor, [Validators.required]],
            condutor: [viagem.condutor, [Validators.required]],
            localSaida: [viagem.localSaida, [Validators.required]],
            hora: [viagem.hora, [Validators.required]],
            odometro: [viagem.odometro, [Validators.required, Validators.min(1)]],
            responsavelVeiculo: [viagem.responsavelVeiculo, [Validators.required]],
            lotacaoVeiculo: [viagem.lotacaoVeiculo, [Validators.required]],
        });
    }

    public async onSubmit() {
        if (!this.form.valid) {
            return;
        }

        this.mensagem.aguarde().then((res) => {
            res.present();
            this.service.salvar(this.form.value as ViagemInicio).subscribe((r) => {
                this.router.navigate(['/', 's', 'veiculo-registrando'], { relativeTo: this.route });
                this.mensagem.sucesso('Viagem iniciada!');
                res.dismiss();
            }, (e) => {
                console.log(e);
                this.mensagem.erro(e);
                res.dismiss();
            });
        });
    }

    initMap() {
        if (!this.posicao || !this.posicao.coords) {
            this.posicao = posicaoEmater();
        }

        if (!this.map) {
            this.map = new Map('localSaidaMap').setView([this.posicao.coords.latitude, this.posicao.coords.longitude], 23);
        }

        tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(this.map);

        this.customMarkerIcon = icon({
            iconUrl: 'assets/img/custom-marker-icon.png',
            iconSize: [64, 64],
            popupAnchor: [0, -20]
        });

        marker([this.posicao.coords.latitude, this.posicao.coords.longitude],
            { icon: this.customMarkerIcon })
            .bindPopup(`<b>Local Saída</b>`, { autoClose: false })
            .on('click', () => console.log('ai'))
            .addTo(this.map).openPopup();
    }

    public pesquisar() {
    }

    public qrCode() {
        this.barcodeScanner.scan().then((r) => {
            console.log('Barcode data', r);
            if (r && r.text && r.text.trim().length) {
                this.form.get('pesquisaVeiculo').setValue(r.text);
            }
        }).catch(err => {
            console.log('Error', err);
        });
    }

    onPress($event, aumenta: boolean) {
        let odometro = this.form.get('odometro').value;
        if (odometro == null) {
            odometro = 0;
        }
        odometro = aumenta ? odometro + 1 : odometro - 1;
        this.form.get('odometro').setValue(odometro > 0 ? odometro : 1);
        console.log(this.form);
    }

}
