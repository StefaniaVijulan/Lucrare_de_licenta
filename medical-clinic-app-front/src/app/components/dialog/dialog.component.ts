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
    this._moderator.newUserS = this.newUsers;

    this.dialogref.close("save");
    this.dialog.open(DialogAddUserComponent,{
     width: '30%'
    }).afterClosed().subscribe(val=>{
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
/*
  myForm!: FormGroup;

  user = new User()
  msg = ''
  actionBtn: string = "Save"
  constructor(private _service: ModeratorService,
    private _router: Router,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA)
    public editData: any,
    private dialogref: MatDialogRef < DialogComponent >
  ) {}

  ngOnInit(): void {
    this.myForm = this.formBuilder.group({
      cnp: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      numberUser: ['', Validators.required],
      emailUser: ['', Validators.compose([Validators.required, Validators.email])]
    });
    console.log("edit before")
    console.log(this.editData);
   if (this.editData) {

      this.myForm.controls['cnp'].setValue(this.editData.cnp);
      this.myForm.controls['firstName'].setValue(this.editData.firstName);
      this.myForm.controls['lastName'].setValue(this.editData.lastName);
      this.myForm.controls['numberUser'].setValue(this.editData.numberUser);
      this.myForm.controls['emailUser'].setValue(this.editData.emailUser);
    }
    console.log("edit")
    console.log(this.editData);
    console.log(this.myForm.value.prenume)
  }

 register(info: any) {
    if (info == "CARDIOLOG") {

      if (!this.editData) {
      
        if (this.myForm.valid) {
          console.log("poate aici")
          console.log(this.myForm.value)
          this._service.addCurant(this.myForm.value).subscribe({
              next: (data) => {

                this._service.count = 1
                this.myForm.reset();
                this.dialogref.close();
              },
              error: () => {

                this.msg = "Error while adding the product";
              }
            }

          )
        }
      } else {
        console.log("edit else")
        console.log(this.editData)
        this.updateProduct(info)
      }

    }
  }
  updateProduct(info: any) {
    console.log("update")
    console.log(info)
    console.log(this.editData.cnp)
    console.log(this.editData)
    this._service.editUser(info, this.editData.cnp, this.myForm.value).subscribe({    
      next: (res) => {
        console.log(res)
        this.myForm.reset();
        this.dialogref.close("update");
      }
    })
  }*/
}