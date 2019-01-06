import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieDatatableComponent } from './movie-datatable.component';

describe('MovieDatatableComponent', () => {
  let component: MovieDatatableComponent;
  let fixture: ComponentFixture<MovieDatatableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieDatatableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieDatatableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
