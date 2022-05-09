import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class DoctorGuard  implements CanActivate {
  constructor(private _service: AuthService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean | UrlTree | Observable < boolean | UrlTree > | Promise < boolean | UrlTree > {
    
    if(this._service.getRole()=="CARDIOLOG") {
        return true;
      } else {
        this.router.navigate(['/dashboard'])
        return false;
      }
  }
}