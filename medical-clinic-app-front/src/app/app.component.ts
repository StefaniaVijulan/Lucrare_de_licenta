import { BreakpointObserver } from '@angular/cdk/layout';
import { AfterViewInit, ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit  {
  title = 'medical-clinic-app-front';

  @ViewChild(MatSidenav,  {static: false})
  sidenav!: MatSidenav;

  constructor(private observer: BreakpointObserver, private cdr: ChangeDetectorRef, public _service:AuthService){

  }
  ngAfterViewInit(){
    this.observer.observe(['(max-width: 2500px']).subscribe((res)=>{
      if(res.matches){
        this.sidenav.mode = 'over';
        this.sidenav.close();
        this.cdr.detectChanges();
      }
      else{
        this.sidenav.mode='side';
        this.sidenav.open();
        this.cdr.detectChanges();
      }
    });
  }
}
