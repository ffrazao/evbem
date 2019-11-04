import { Component, OnInit, ViewChild } from '@angular/core';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Router, ActivatedRoute } from '@angular/router';
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import { Map, tileLayer, marker, icon } from 'leaflet';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { MensagemService } from '../../../../comum/servico/mensagem/mensagem.service';
import { posicaoEmater } from '../../../../comum/ferramenta/funcao';
import { SqliteService } from '../../../../comum/servico/local/sqlite.service';
import { ViagemDaoLocal } from '../../../../dao/local/veiculo/viagem-dao.local';
import { VeiculoDao } from '../../../../dao/externo/veiculo/veiculo-dao';
import { ViagemInicio } from './viagem-inicio';
import { VeiculoFiltroDto } from 'src/app/transporte/veiculo/veiculo.filtro.dto';
import { ViagemDao } from 'src/app/dao/externo/veiculo/viagem-dao';
import { PessoaDao } from 'src/app/dao/externo/principal/pessoa-dao';
import { PessoaFiltroDto } from 'src/app/transporte/principal/pessoa.filtro.dto';
import { EscolheItemService } from 'src/app/comum/componente/escolhe-item/escolhe-item.service';

@Component({
    templateUrl: './registro.component.html',
    styleUrls: ['./registro.component.scss']
})
export class RegistroComponent implements OnInit {

    private form: FormGroup;

    private entidade = new ViagemInicio();

    @ViewChild('focaliza', { static: false })
    private focaliza;

    private map = null;

    private customMarkerIcon = null;

    private posicao: Geoposition = posicaoEmater();

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private servico: ViagemDao,
        private servicoVeiculo: VeiculoDao,
        private servicoPessoa: PessoaDao,
        private servicoLocal: ViagemDaoLocal,
        private formBuilder: FormBuilder,
        private mensagem: MensagemService,
        private geo: Geolocation,
        private barcodeScanner: BarcodeScanner,
        private sqliteService: SqliteService,
        private escolheItem: EscolheItemService,
    ) {
    }

    async ngOnInit() {
        try {
            this.form = this.createForm(this.entidade, '1234', 'Fernando Frazão da Silva');
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
            // new [this.form.value as ViagemInicio]
            this.servicoVeiculo.salvar([]).subscribe((r) => {
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

    public pesquisarVeiculo() {
        if (!this.form.get('pesquisaVeiculo').value) {
            this.mensagem.erro('Conteúdo da pesquisa não informado!');
            return;
        }

        this.mensagem.aguarde().then((res) => {
            res.present();
            // new [this.form.value as ViagemInicio]
            const filtro = new VeiculoFiltroDto();
            filtro.pesq = [];
            filtro.pesq[0] = this.form.get('pesquisaVeiculo').value;
            this.servicoVeiculo.listar(filtro).subscribe((r) => {
                console.log(r[0]);
                this.form.get('veiculo').setValue(r[0]);
                this.entidade.veiculo = r[0];
                res.dismiss();
            }, (e) => {
                console.log(e);
                this.mensagem.erro(e);
                res.dismiss();
            });
        });
    }

    public pesquisarCondutor() {
        const itens = ['a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c',
            // 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b',
            // 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a',
            // 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c',
            // 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b',
            // 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a',
            // 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c',
            // 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b',
            // 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a',
            // 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c',
            // 'a', 'b', 'c', 'a', 'b', 'c'
        ];

        this.escolheItem.escolhe(itens, false).then(r => console.log(r));
        return;

        if (!this.form.get('pesquisaCondutor').value) {
            this.mensagem.erro('Conteúdo da pesquisa não informado!');
            return;
        }

        this.mensagem.aguarde().then((res) => {
            res.present();
            // new [this.form.value as ViagemInicio]
            const filtro = new PessoaFiltroDto();
            filtro.pesq = [];
            filtro.pesq[0] = this.form.get('pesquisaCondutor').value;
            this.servicoPessoa.listar(filtro).subscribe((r) => {
                if (r) {
                    console.log(r[0]);
                    this.form.get('condutor').setValue(r[0]);
                    this.entidade.condutor = r[0];
                }
                res.dismiss();
            }, (e) => {
                console.log(e);
                this.mensagem.erro(e);
                res.dismiss();
            });
        });
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
