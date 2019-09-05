import { Component, OnInit } from '@angular/core';
import * as CanvasJS from 'src/app/canvasjs.min';
import {ActivatedRoute, Router} from "@angular/router";
import {HotelService} from "../../service/hotel.service";
import {AdditionalServiceService} from "../../service/additionalService.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-attendance-graphs',
  templateUrl: './attendance-graphs.component.html',
  styleUrls: ['./attendance-graphs.component.css']
})
export class AttendanceGraphsComponent implements OnInit {

  public formMonthly: FormGroup;
  public year: AbstractControl;
  nazivHotela: string;
  showGraph: boolean;
  chooseMonthy: boolean;
  chooseDaily: boolean;
  chooseWeekly: boolean;

  monthlyValues = [];

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              public fb: FormBuilder,
              private hotelService: HotelService) {
    this.formMonthly = this.fb.group({
      'year': ['', Validators.compose([Validators.required])],
    })
    this.year = this.formMonthly.controls['year'];
  }

  ngOnInit() {

    this.showGraph = false;
    this.chooseMonthy = false;
    this.chooseDaily = false;
    this.chooseWeekly = false;

    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

  }

  goBack() {
    this.router.navigateByUrl('hotelAdminPage' );

  }

  confirmMonthly() {

    const idHotela = this.route.snapshot.params.idHotela;
    this.hotelService.monthyGraph(idHotela, this.year.value).subscribe(data => {
      this.monthlyValues = data;


      let chart = new CanvasJS.Chart("chartContainer1", {
        animationEnabled: true,
        exportEnabled: true,
        title: {
          text: "Grafik posecenosti na mesecnom nivou"
        },
        data: [{
          type: "column",
          dataPoints: [
            {y: this.monthlyValues[0], label: "Januar"},
            {y: this.monthlyValues[1], label: "Februar"},
            {y: this.monthlyValues[2], label: "Mart"},
            {y: this.monthlyValues[3], label: "April"},
            {y: this.monthlyValues[4], label: "Maj"},
            {y: this.monthlyValues[5], label: "Jun"},
            {y: this.monthlyValues[6], label: "Jul"},
            {y: this.monthlyValues[7], label: "Avgust"},
            {y: this.monthlyValues[8], label: "Septembar"},
            {y: this.monthlyValues[9], label: "Oktobar"},
            {y: this.monthlyValues[10], label: "Novembar"}
          ]
        }]
      });

      chart.render();

    });

    this.showGraph = true;
  }

  showMonthlyGraph() {
    this.chooseMonthy = true;
    this.chooseDaily = false;
    this.chooseWeekly = false;
    this.showGraph = false;
  }

  showWeeklyGraph() {
    this.chooseMonthy = false;
    this.chooseDaily = false;
    this.chooseWeekly = true;
    this.showGraph = false;

  }

  showDailyGraph() {
    this.showGraph = false;
    this.chooseMonthy = false;
    this.chooseDaily = true;
    this.chooseWeekly = false;
  }
}
