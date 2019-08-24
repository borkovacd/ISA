import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from "../../service/vozilo.service";
import {RentCarService} from "../../service/rentcar.service";

@Component({
  selector: 'app-vozila',
  templateUrl: './vozila.component.html',
  styleUrls: ['./vozila.component.css']
})

export class VozilaComponent implements OnInit {

  vozila = []
  idVozila : any;
  nazivRent : string;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private voziloService:VoziloService,
              private rentService: RentCarService) { }

  ngOnInit() {
  }

}
