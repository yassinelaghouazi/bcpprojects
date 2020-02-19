export interface IMaitreOuvrage {
  id?: number;
  idMaitreOuvrage?: number;
  nom?: string;
  email?: string;
  tel?: string;
  contactPersonne?: string;
  bopportunityId?: number;
  cautionId?: number;
}

export class MaitreOuvrage implements IMaitreOuvrage {
  constructor(
    public id?: number,
    public idMaitreOuvrage?: number,
    public nom?: string,
    public email?: string,
    public tel?: string,
    public contactPersonne?: string,
    public bopportunityId?: number,
    public cautionId?: number
  ) {}
}
