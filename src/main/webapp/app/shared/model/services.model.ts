export interface IServices {
  id?: number;
  idServices?: number;
  servicesDesc?: string;
  ordersServicesId?: number;
}

export class Services implements IServices {
  constructor(public id?: number, public idServices?: number, public servicesDesc?: string, public ordersServicesId?: number) {}
}
