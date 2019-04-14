import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ValidateEmailFormComponent} from './validate-email-form.component';

describe('ValidateEmailFormComponent', () => {
  let component: ValidateEmailFormComponent;
  let fixture: ComponentFixture<ValidateEmailFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ValidateEmailFormComponent]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidateEmailFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
