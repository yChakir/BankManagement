import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RightsPageComponent } from './rights-page.component';

describe('RightsPageComponent', () => {
  let component: RightsPageComponent;
  let fixture: ComponentFixture<RightsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RightsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RightsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
