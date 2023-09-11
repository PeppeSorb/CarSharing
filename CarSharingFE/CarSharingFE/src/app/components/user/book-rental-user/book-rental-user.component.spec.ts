import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookRentalUserComponent } from './book-rental-user.component';

describe('BookRentalUserComponent', () => {
  let component: BookRentalUserComponent;
  let fixture: ComponentFixture<BookRentalUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookRentalUserComponent]
    });
    fixture = TestBed.createComponent(BookRentalUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
