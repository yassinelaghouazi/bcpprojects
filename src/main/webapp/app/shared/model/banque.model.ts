export interface IBanque {
  id?: number;
  idBanque?: number;
  banque?: string;
  contactEmail?: string;
  contactTel?: string;
  adresseAgence?: string;
  cautionId?: number;
}

export class Banque implements IBanque {
  constructor(
    public id?: number,
    public idBanque?: number,
    public banque?: string,
    public contactEmail?: string,
    public contactTel?: string,
    public adresseAgence?: string,
    public cautionId?: number
  ) {}
}
