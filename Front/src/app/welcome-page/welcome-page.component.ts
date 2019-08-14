import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';


@Component({
  selector    : 'welcome-page',
  templateUrl : './welcome-page.component.html',
  styleUrls   : ['./welcome-page.component.scss']
})
export class WelcomePageComponent implements OnInit {

  routeLinks: any[];
  activeLinkIndex = -1;
  navLinks: any;

  constructor(
    private router : Router
  ){
    this.routeLinks = [
      {
        label  : 'Tab 1',
        link   : './tab-1-link',
        index  : 0
      },
      {
        label  : 'Tab 2',
        link   : './tab-2-link',
        index  : 1
      },
      {
        label  : 'Tab 3',
        link   : './tab-3-link',
        index  : 2
      }
    ];
  }

  ngOnInit() {
    this.activeLinkIndex = this._markingTabLogic(this.router);

    this.router.events.subscribe((res) => {
//A bit generic url matching
//this.activeLinkIndex =this.routeLinks.indexOf(this.routeLinks.find(tab => tab.link === '.' + this.router.url));

//A more fine grain matching
      this.activeLinkIndex = this._markingTabLogic(this.router);
    });
  }

  _markingTabLogic(router){
    if(router.url.indexOf('tab-3-link') > -1){
      return 2;
    }
    else if(router.url.indexOf('tab-2-link') > -1){
      return 1;
    }
    return 0;
  }

}
