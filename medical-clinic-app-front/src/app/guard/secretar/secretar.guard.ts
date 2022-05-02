import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class SecretarGuard implements CanActivate {
  constructor(private _service: AuthService, private router: Router) {
  }

  canActivate( route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean | UrlTree | Observable < boolean | UrlTree > | Promise < boolean | UrlTree > {
    
    if(this._service.getRole()=="SECRETAR") {
        return true;
      } else {
        this.router.navigate(['/home'])
        return false;
      }
  
  }
  
}
