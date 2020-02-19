export interface IModePaiement {
  id?: number;
  idModePaiement?: number;
  modePaiement?: string;
  reglementId?: number;
}

export class ModePaiement implements IModePaiement {
  constructor(public id?: number, public idModePaiement?: number, public modePaiement?: string, public reglementId?: number) {}
}
