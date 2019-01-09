export class MovieForm {
  constructor(
    public title: string,
    public genre: string,
    public ageLimit: string,
    public duration: number,
    public releaseYear: number,
    public country: string,
    public description: string,
    public image: File,
  ) {

  }
}
