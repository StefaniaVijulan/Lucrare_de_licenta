import { animate, state, style, transition, trigger } from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Appointment } from 'src/app/interfaces/appointment';
import { AuthService } from 'src/app/services/auth/auth.service';
import { SecretarService } from 'src/app/services/secretar/secretar.service';

@Component({
  selector: 'app-secretar-appointment',
  templateUrl: './secretar-appointment.component.html',
  styleUrls: ['./secretar-appointment.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class SecretarAppointmentComponent implements OnInit {
  dataSource !: MatTableDataSource<any>;
  columnsToDisplay = ['dataA','hour','cnp','firstName', 'lastName', 'emailUser', 'numberUser'];
  expandedElement: Appointment | null;

  @ViewChild(MatPaginator, {static: true}) 
  paginator!: MatPaginator;

  @ViewChild(MatSort, {static: true}) 
  sort!: MatSort;

  constructor(private dialog: MatDialog, 
    public _secretar: SecretarService,
     private _http: HttpClient, 
      public _service: AuthService) {
       
       }
       ngOnInit(): void {
       // this.paginator._intl.itemsPerPageLabel="Numarul de elemente afisate:";
        this.allFutureAppointments();
       }
       allFutureAppointments(){
    
        return this._secretar.getAppointments()
        .subscribe((res:any)=>{
            this.dataSource =  new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        })
}
 applyFilter(event: Event) {
          const filterValue = (event.target as HTMLInputElement).value;
          this.dataSource.filter = filterValue.trim().toLowerCase();
        }
    


};