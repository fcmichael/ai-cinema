import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieReserveComponent } from './movie-reserve.component';

describe('MovieReserveComponent', () => {
  let component: MovieReserveComponent;
  let fixture: ComponentFixture<MovieReserveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieReserveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieReserveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
