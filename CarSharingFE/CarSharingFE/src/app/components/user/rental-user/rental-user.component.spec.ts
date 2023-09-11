import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentalUserComponent } from './rental-user.component';

describe('RentalUserComponent', () => {
  let component: RentalUserComponent;
  let fixture: ComponentFixture<RentalUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RentalUserComponent]
    });
    fixture = TestBed.createComponent(RentalUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
