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

  public form: FormGroup;
  public year3: AbstractControl;
  public month3: AbstractControl;
  public week3: AbstractControl;
  nazivHotela: string;
  showGraph: boolean;
  choose: boolean;
  chooseMonthy: boolean;
  chooseDaily: boolean;
  chooseWeekly: boolean;

  monthlyValues = [];

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              public fb: FormBuilder,
              private hotelService: HotelService) {
    this.form = this.fb.group({
      'year3': ['', Validators.compose([Validators.pattern('(19[789]\\d|20[01]\\d)')])],
      'month3': ['', Validators.compose([Validators.pattern('^(1[012]|[1-9])$')])],
      'week3': [''],
    });
    this.year3 = this.form.controls['year3'];
    this.month3 = this.form.controls['month3'];
    this.week3 = this.form.controls['week3'];

  }

  ngOnInit() {

    this.showGraph = false;
    this.choose = false;
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

  confirm() {

    const idHotela = this.route.snapshot.params.idHotela;
    if(this.chooseMonthy) {
      if(this.year3.value == '') {
        alert("Unos godine je obavezan!");
        return;
      }
      this.hotelService.monthyGraph(idHotela, this.year3.value).subscribe(data => {
        this.monthlyValues = data;

        let chart = new CanvasJS.Chart("chartContainer1", {
          animationEnabled: true,
          exportEnabled: true,
          title: {
            text: "Grafik posecenosti na godisnjem nivou"
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
              {y: this.monthlyValues[10], label: "Novembar"},
              {y: this.monthlyValues[11], label: "Decembar"}
            ]
          }]
        });
        chart.render();
      });
    } else if(this.chooseWeekly) {
      if(this.year3.value == '') {
        alert("Unos godine je obavezan!");
        return;
      }
      if(this.month3.value == '') {
        alert("Unos meseca je obavezan!");
        return;
      }
      this.hotelService.weeklyGraph(idHotela, this.year3.value, this.month3.value).subscribe(data => {
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
              {y: this.monthlyValues[0], label: "Nedelja 1"},
              {y: this.monthlyValues[1], label: "Nedelja 2"},
              {y: this.monthlyValues[2], label: "Nedelja 3"},
              {y: this.monthlyValues[3], label: "Nedelja 4"},
              {y: this.monthlyValues[4], label: "Nedelja 5"}
            ]
          }]
        });
        chart.render();
      });
    } else if(this.chooseDaily) {
      if(this.week3.value == '') {
        alert("Izbor datuma je obavezan!");
        return;
      }
      this.hotelService.dailyGraph(idHotela, this.week3.value).subscribe(data => {
        this.monthlyValues = data;

        let chart = new CanvasJS.Chart("chartContainer1", {
          animationEnabled: true,
          exportEnabled: true,
          title: {
            text: "Grafik posecenosti na nedeljnom nivou"
          },
          data: [{
            type: "column",
            dataPoints: [
              {y: this.monthlyValues[0], label: "Dan 1"},
              {y: this.monthlyValues[1], label: "Dan 2"},
              {y: this.monthlyValues[2], label: "Dan 3"},
              {y: this.monthlyValues[3], label: "Dan 4"},
              {y: this.monthlyValues[4], label: "Dan 5"},
              {y: this.monthlyValues[5], label: "Dan 6"},
              {y: this.monthlyValues[6], label: "Dan 7"}
            ]
          }]
        });
        chart.render();
      });
    }
    this.showGraph = true;
  }

  showMonthlyGraph() {
    this.chooseMonthy = true;
    this.chooseDaily = false;
    this.chooseWeekly = false;
    this.showGraph = false;
    this.choose = true;
  }

  showWeeklyGraph() {
    this.chooseMonthy = false;
    this.chooseDaily = false;
    this.chooseWeekly = true;
    this.showGraph = false;
    this.choose = true;
  }

  showDailyGraph() {
    this.showGraph = false;
    this.chooseMonthy = false;
    this.chooseDaily = true;
    this.chooseWeekly = false;
    this.choose = true;
  }
}
