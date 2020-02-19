import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BtpprojectsSharedModule } from 'app/shared/shared.module';
import { ModePaiementComponent } from './mode-paiement.component';
import { ModePaiementDetailComponent } from './mode-paiement-detail.component';
import { ModePaiementUpdateComponent } from './mode-paiement-update.component';
import { ModePaiementDeleteDialogComponent } from './mode-paiement-delete-dialog.component';
import { modePaiementRoute } from './mode-paiement.route';

@NgModule({
  imports: [BtpprojectsSharedModule, RouterModule.forChild(modePaiementRoute)],
  declarations: [ModePaiementComponent, ModePaiementDetailComponent, ModePaiementUpdateComponent, ModePaiementDeleteDialogComponent],
  entryComponents: [ModePaiementDeleteDialogComponent]
})
export class BtpprojectsModePaiementModule {}
