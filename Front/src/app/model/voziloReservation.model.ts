export class  VoziloReservationModel {
  constructor(public startDate: string,
              public endDate: string,
              public vozilo: any,
              public mestoPreuzimanja: string,
              public mestoVracanja: string){}
}
