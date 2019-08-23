export class  KorisnikProfilModel {
  constructor(public ime: string,
              public prezime: string,
              public lozinka: string,
              public ponovljenaLozinka: string,
              public email: string,
              public telefon: string,
              public grad: string) {}
}
