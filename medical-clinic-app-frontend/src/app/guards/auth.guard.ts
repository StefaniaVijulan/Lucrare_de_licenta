import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot,RouterStateSnapshot,UrlTree,CanActivate, Router} from '@angular/router';
import {Observable} from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private _service: AuthService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean | UrlTree | Observable < boolean | UrlTree > | Promise < boolean | UrlTree > {
    const token = localStorage.getItem('token')

    if(this._service.loggedIn()) {
    return true;
  } else {
    this.router.navigate(['/login-doctor'])
    return false;
  }
  }

}