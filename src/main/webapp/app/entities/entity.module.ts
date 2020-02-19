import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'banque',
        loadChildren: () => import('./banque/banque.module').then(m => m.BtpprojectsBanqueModule)
      },
      {
        path: 'bopportunity',
        loadChildren: () => import('./bopportunity/bopportunity.module').then(m => m.BtpprojectsBopportunityModule)
      },
      {
        path: 'caution',
        loadChildren: () => import('./caution/caution.module').then(m => m.BtpprojectsCautionModule)
      },
      {
        path: 'company',
        loadChildren: () => import('./company/company.module').then(m => m.BtpprojectsCompanyModule)
      },
      {
        path: 'delivery',
        loadChildren: () => import('./delivery/delivery.module').then(m => m.BtpprojectsDeliveryModule)
      },
      {
        path: 'maitre-ouvrage',
        loadChildren: () => import('./maitre-ouvrage/maitre-ouvrage.module').then(m => m.BtpprojectsMaitreOuvrageModule)
      },
      {
        path: 'mode-paiement',
        loadChildren: () => import('./mode-paiement/mode-paiement.module').then(m => m.BtpprojectsModePaiementModule)
      },
      {
        path: 'orders',
        loadChildren: () => import('./orders/orders.module').then(m => m.BtpprojectsOrdersModule)
      },
      {
        path: 'orders-products',
        loadChildren: () => import('./orders-products/orders-products.module').then(m => m.BtpprojectsOrdersProductsModule)
      },
      {
        path: 'orders-services',
        loadChildren: () => import('./orders-services/orders-services.module').then(m => m.BtpprojectsOrdersServicesModule)
      },
      {
        path: 'products',
        loadChildren: () => import('./products/products.module').then(m => m.BtpprojectsProductsModule)
      },
      {
        path: 'project',
        loadChildren: () => import('./project/project.module').then(m => m.BtpprojectsProjectModule)
      },
      {
        path: 'provider',
        loadChildren: () => import('./provider/provider.module').then(m => m.BtpprojectsProviderModule)
      },
      {
        path: 'reglement',
        loadChildren: () => import('./reglement/reglement.module').then(m => m.BtpprojectsReglementModule)
      },
      {
        path: 'services',
        loadChildren: () => import('./services/services.module').then(m => m.BtpprojectsServicesModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class BtpprojectsEntityModule {}
