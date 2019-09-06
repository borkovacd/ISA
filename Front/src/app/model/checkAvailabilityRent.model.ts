export class CheckAvailabilityRentModel {
  constructor(public startDate: string,
              public endDate: string,
              public mestoPreuzimanja: string,
              public mestoVracanja: string,
              public numberOfGuests: string,
              public tipVozila: string,
              public priceRange: string,
  )  {}
}


