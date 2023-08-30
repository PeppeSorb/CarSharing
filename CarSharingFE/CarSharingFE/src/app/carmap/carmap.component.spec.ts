import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarmapComponent } from './carmap.component';

describe('CarmapComponent', () => {
  let component: CarmapComponent;
  let fixture: ComponentFixture<CarmapComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarmapComponent]
    });
    fixture = TestBed.createComponent(CarmapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
