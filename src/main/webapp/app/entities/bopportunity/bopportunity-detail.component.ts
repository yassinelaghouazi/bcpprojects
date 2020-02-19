import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBopportunity } from 'app/shared/model/bopportunity.model';

@Component({
  selector: 'jhi-bopportunity-detail',
  templateUrl: './bopportunity-detail.component.html'
})
export class BopportunityDetailComponent implements OnInit {
  bopportunity: IBopportunity | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bopportunity }) => (this.bopportunity = bopportunity));
  }

  previousState(): void {
    window.history.back();
  }
}
