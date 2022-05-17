import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AngularFireStorage, AngularFireStorageReference } from '@angular/fire/storage';

@Component({
  selector: 'app-upload-img',
  templateUrl: './upload-img.component.html',
  styleUrls: ['./upload-img.component.scss']
})
export class UploadImgComponent implements OnInit {
  selectedFile: File;
  ref: AngularFireStorageReference;
  downloadURL: string;
  checkUploadImg=false;
  @Output()
  giveURLtoCreate = new EventEmitter<string>();

  constructor(private afStorage: AngularFireStorage) { }

  ngOnInit() {
  }
  onFileChandes($event){
    this.selectedFile = $event.target.files[0];
    console.log(this.selectedFile)
  }
  onUpload(){
    this.checkUploadImg = true;
    const id = Math.random().toString(36).substring(2);
    this.ref = this.afStorage.ref(id);
    console.log("ref=>", this.ref)
    this.ref.put(this.selectedFile).then(snapshot=>{
      console.log(snapshot.ref.getDownloadURL());
      return snapshot.ref.getDownloadURL();
    }).then(downloadURL =>{
      this.downloadURL = downloadURL;
      this.giveURLtoCreate.emit(this.downloadURL);
      this.checkUploadImg = false;
      return downloadURL;
    }).catch(error=>{
        console.log(`Faild to uploade avatar and get link ${error}`)
    });
  
  }
}
