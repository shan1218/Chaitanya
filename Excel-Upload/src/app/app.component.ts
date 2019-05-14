import { Component, OnDestroy } from '@angular/core';
import { UploadEvent, UploadFile, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {
  private unsubscribe: Subject<void> = new Subject();
  public files: UploadFile[] = [];
  public uploadingFiles = false;
  constructor(private http: HttpClient) { }

  private afterUpload(index: number, totalLength: number): void {
    if ((index + 1) === totalLength) {
      this.uploadingFiles = false;
    }
  }

  public dropped(event: UploadEvent): void {
    this.uploadingFiles = true;
    this.files = [];
    event.files.forEach((droppedFile, index) => {
      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {

          // Here you can access the real file
          console.log(droppedFile.relativePath, file);


          // You could upload it like this:
          const formData = new FormData();
          formData.append('file', file, droppedFile.relativePath);

          this.http.post('http://localhost:6005/ExcelUpload/rest/song/uploadSong', formData, { responseType: 'blob' })
            .pipe(takeUntil(this.unsubscribe))
            .subscribe(data => {
              // Sanitized logo returned from backend
              this.files.push(droppedFile);
              this.afterUpload(index, event.files.length);
            }, () => {
              this.afterUpload(index, event.files.length);
            });


        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    });
  }

  public fileOver(event) {
    console.log(event);
  }

  public fileLeave(event) {
    console.log(event);
  }

  ngOnDestroy(): void {
    this.unsubscribe.unsubscribe();
  }
}
