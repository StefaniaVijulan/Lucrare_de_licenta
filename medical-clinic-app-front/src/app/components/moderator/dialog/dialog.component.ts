import {  Component,  Inject,  OnInit} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';
import { DialogAddUserComponent } from '../dialog-add-user/dialog-add-user.component';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent {
  myForm!: FormGroup;
  listuser = []
  newUsers: string = 'Imagist';
  usersList: string[] = [ 'Imagist', 'Secretar','Cardiolog', 'Hematolog'];

  constructor(
    private _moderator: ModeratorService,
    private dialogref: MatDialogRef < DialogComponent >,
    private dialog: MatDialog ){}
  openDialog(){
    
    this._moderator.newUserRole = this.newUsers.toUpperCase();
    console.log(this._moderator.newUserRole)
    this.dialogref.close("save");
    this.dialog.open(DialogAddUserComponent,{
     width: '30%'
    }).afterClosed().subscribe(val=>{
      location.reload()
      console.log("aici")
      console.log(val)
      if(val === "save"){
        console.log("add dialog ",val)
        this.allUsers();
      }
    });

 };
 allUsers(){
  return this._moderator.getAllUsers()
  .subscribe((res:any)=>{
   this.usersList = res
  }
 )};

}