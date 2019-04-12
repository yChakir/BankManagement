import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidateEmailPageComponent } from './validate-email-page.component';

describe('ValidateEmailPageComponent', () => {
  let component: ValidateEmailPageComponent;
  let fixture: ComponentFixture<ValidateEmailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidateEmailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidateEmailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
