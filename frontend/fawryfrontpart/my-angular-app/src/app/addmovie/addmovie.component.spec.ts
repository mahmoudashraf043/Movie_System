import { ComponentFixture, TestBed } from '@angular/core/testing';

import { addmovieComponent } from './addmovie.component';

describe('CreatemovieComponent', () => {
  let component: addmovieComponent;
  let fixture: ComponentFixture<addmovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [addmovieComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(addmovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
