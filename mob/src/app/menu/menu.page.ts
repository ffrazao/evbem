import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: 'menu.page.html',
  styleUrls: ['menu.page.scss']
})
export class MenuPage implements OnInit {
  
  public appPages = [
    {
      title: 'Home',
      url: '/m/home',
      icon: 'home'
    },
    {
      title: 'Login',
      url: '/login',
      icon: 'key'
    },
    {
      title: 'Veículo',
      url: '/m/s/veiculo',
      icon: 'car'
    },
    {
      title: 'List (Não Seguro)',
      url: '/list',
      icon: 'list'
    },
    {
      title: 'List (Seguro)',
      url: '/s/list',
      icon: 'list'
    },
  ];

  constructor(
  ) {
  }

  ngOnInit() {
  }

}
