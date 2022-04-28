import { BreakpointObserver } from '@angular/cdk/layout';
import { AfterViewInit, ChangeDetectorRef, Component, HostListener, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material';
import { ModeratorComponent } from './pages/moderator/moderator.component';
import { AuthService } from './services/auth/auth.service';
import { ModeratorService } from './services/moderator/moderator.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit  {
  title = 'medical-clinic-app-front';

  @ViewChild(MatSidenav,  {static: false})
  sidenav!: MatSidenav;
  @HostListener('window:beforeunload')
  unloadHandler(event) {
    sessionStorage.removeItem('token')
    sessionStorage.removeItem('role')
    sessionStorage.removeItem('user')
    
  }
  constructor(public _moderator:ModeratorService, private observer: BreakpointObserver, private cdr: ChangeDetectorRef, public _service:AuthService){

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